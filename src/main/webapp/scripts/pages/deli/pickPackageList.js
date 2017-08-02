/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */

/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
$(function(){
	//给支付时间初始值
	if($("#q_time_paymentTimeSta").val()==''){
		var paymentTimeSta = new Date();
		paymentTimeSta.setDate(paymentTimeSta.getDate()-1); 
		var time2 = paymentTimeSta.Format("yyyy-MM-dd 00:00");
		$("#q_time_paymentTimeSta").val(time2);
	}
	
	if($("#q_time_paymentTimeEnd").val()==''){
		var paymentTimeEnd = new Date();
		var time = paymentTimeEnd.Format("yyyy-MM-dd hh:mm");
		$("#q_time_paymentTimeEnd").val(time);
	}
	
	
	$("#queryFormBtn").on("click", function() {
		
		if($("#q_time_paymentTimeSta").val()==""&& 
				$("#q_time_paymentTimeEnd").val()==""){
			wms.frame.notifyError(i18n.t("return-paytime-not-null"));
			return;
		}
		if($("#q_time_paymentTimeSta").val()==""&&$("#q_time_paymentTimeEnd").val()!=""){
			wms.frame.notifyError(i18n.t("deli-pay-time-start"));
			return;
		}
		if($("#q_time_paymentTimeSta").val()!=""&&$("#q_time_paymentTimeEnd").val()==""){
			wms.frame.notifyError(i18n.t("deli-pay-time-end"));
			return;
		}			
		if($("#q_time_paymentTimeEnd").val()<$("#q_time_paymentTimeSta").val()){
			wms.frame.notifyError(i18n.t("deli-endTime-over-startTime"));
			return;
		}			
		
		search();
	});
});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}

function findPickPackageDetailById(id){
	wms.asyncPost(pagebase + "/deli/pickpackage/pickPackageDetail" , {"id":id}, {
		successHandler : function(data, textStatus) {
			$("#code").html(data.code);			
			$("#orderCode").html(data.orderCode);
			var text = "";
			if (data.status == 0) {
				text = i18n.t("");
			} else if (data.status == 1) {
				text = i18n.t("ppackage-status-create");
			} else if (data.status == 2) {
				text = i18n.t("ppackage-status-ssign");
			} else if (data.status == 3) {
				text = i18n.t("ppackage-status-csign");
			} else if (data.status == 4) {
				text = i18n.t("ppackage-status-expired");
			} else if (data.status == 7) {
				text = i18n.t("ppackage-status-return");
			} else if (data.status == 8) {
				text = i18n.t("ppackage-status-wreturn");
			}
			$("#status").html(text);			
			$("#pgCode").html(data.pgCode);
			$("#receiver").html(data.receiver);
			$("#phone").html(data.receiverPhone);
			$("#address").html(data.address);
			$("#channelCode").html(data.channelCode);
			$("#storeName").html(data.storeName);
			$("#storeCode").html(data.storeCode);
			var alltotalPrice=0;
			$("#packageLine").find("tbody").empty();
			$.each(data.packageLineList, function(index, sku) {
				var trHtml = "<tr><td>" + sku.skuCode + "</td><td>"
						+ sku.skuName + "</td><td>" + sku.unitPrice
						+ "</td><td>" + sku.quantity + "</td><td>"
						+ sku.totalPrice + "</td></tr>";
				alltotalPrice = alltotalPrice + sku.totalPrice;
				$("#packageLine").append(trHtml);
			});
			
			$("#totalPrice").html(alltotalPrice);	
		}
	});
}


//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}