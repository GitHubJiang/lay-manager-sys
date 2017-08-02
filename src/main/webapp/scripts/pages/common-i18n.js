/**通用js国际化  中文*/
wms.addResource("zh_CN", {
	"dashboard_welcome": "欢迎使用驻店宝",
	/**分页条*/
    "pager-first": "第一页",
    "pager-last": "末页",
    "pager-prev": "前一页",
    "pager-next": "后一页",
    /**通用校验*/
	"validate-minlength": "输入长度不足",
    "validate-invalid-number": "不是一个合法的数字或精度要求不符合要求",
    "validate-invalid-number-range": "数据超出范围，只能在%s和%s之间",
    "validate-required" : "请输入内容",
    "common-on" : "已启用",
    "common-off" : "已禁用",
	/**user*/
	"user-choose-store" : "请选择所属门店",
	"user-only-number" : "账号和密码不能包含中文",
	"user-confirm-password" : "确认密码输入不一致",
	"user-exist" : "此账号已存在",
	"zero-data" : "数据列表为空,无法导出,请重新选择搜索条件",
	/**APPU*/
	"appu-upload-success" : "上传成功",
	"appu-version-exist" : "此版本已存在",
	"appu-upload-first" : "请先上传文件",
	"appu-operate-success" : "操作成功",
    /**deli*/
    "deli-pay-time-start" : "请填写订单付款开始时间",
    "deli-pay-time-end" : "请填写订单付款结束时间",
    "deli-distribute-time-start" : "请填写配货完成时间的开始时间",
    "deli-distribute-time-end" : "请填写配货完成时间的结束时间",
    "deli-payEndTime-over-payStartTime" : "订单付款结束时间应该大于订单付款开始时间",
    "deli-time-not-null" : "完成配货时间和订单付款时间这两个查询条件不允许同时为空",
    "deli-endTime-over-startTime" : "结束时间应该大于开始时间",
    "deli-status-success" : "已配货",
    "deli-status-wpick" : "待接单",
    "deli-status-failed" : "配货失败",
    "deli-status-wdeli" : "待配货",
    "deli-status-wview" : "待拣货",
    "deli-status-wcheck" : "待复核",
    "deli-status-create" : "新建",
    /**brand*/
    "brand-code-exist" : "此编码已存在",
    "brand-inv-range-over" : "第二行的库存上限须大于第一行的库存上限",
    "brand-inv-range-less" : "第一行的库存上限须小于第二行的库存上限",
    "brand-number-range" : "请输入不小于零的整数",
    /**Store*/
	"store-checkincludingletterstring":"请至少使用一个字母",
	"store-modifyformcpd":"请选择省市区",
	"store-exportstorestring":"数据列表为空,无法导出,请重新选择搜索条件",
	"store-uploadsucess":"上传成功",
	"store-checkuniquestorecode":"此编码已经存在",
	"store-on":"已启用",
	"store-off":"已禁用",
	"store-on-succeed":"启用",
	"store-off-succeed":"禁用",
	
	/**return*/
	"return-status-wexpresspick" : "待快递取件",
	"return-status-expresspicked" : "快递已取件",
	"order-total-price": "商品总金额",
	"return-paytime-not-null":"订单付款时间不允许为空",
	/**pickpackage*/
	"ppackage-status-create" : "预计到店",
	"ppackage-status-ssign" : "门店已签收",
	"ppackage-status-csign" : "顾客已取件",
	"ppackage-status-expired" : "超期件",	
	"ppackage-status-return" : "已退货",
	"ppackage-status-wreturn" : "待退货"
	
});

/**英文*/
wms.addResource("en", {
	"dashboard_welcome": "Welcome to use Shopdog",
	/**分页条*/
    "pager-first": "First Page",
    "pager-last": "Last Page",
    "pager-prev": "Previous Page",
    "pager-next": "Next Page",
    /**通用校验*/
	"validate-minlength": "Insufficient Length",
    "validate-invalid-number": "Not a valid Number or Decimal",
    "validate-invalid-number-range": "Data is out of range, only between%s and%s",
    "validate-required" : "Please enter",
    "common-on" : "Enabled",
    "common-off" : "Disabled",
	/**user*/
	"user-choose-store" : "Please select your store",
	"user-only-number" : "Account and password cannot contain Chinese",
	"user-confirm-password" : "Your passwords do not match.",
	"user-exist" : "This account already exists",
	"zero-data" : "Cannot export empty data, please select search criteria again",
	/**APPU*/
	"appu-upload-success" : "Upload Success",
	"appu-version-exist" : "This version already exists",
	"appu-upload-first" : "Please upload a file",
	"appu-operate-success" : "Success",
    /**deli*/
    "deli-pay-time-start" : "Please enter the start time of order payment",
    "deli-pay-time-end" : "Please enter the end time of order payment",
    "deli-distribute-time-start" : "Please enter the start time of packing",
    "deli-distribute-time-end" : "Please enter the end time of packing",
    "deli-payEndTime-over-payStartTime" : "The end time order payment  should be later than the start time",
    "deli-time-not-null" : "Either time or order payment time is not null",
    "deli-endTime-over-startTime" : "The end time should be later than the start time",
    "deli-status-success" : "Packed",
    "deli-status-wpick" : "Unprocessed",
    "deli-status-failed" : "Failed",
    "deli-status-wdeli" : "Pending Delivery",
    "deli-status-wview" : "Pending Picking",
    "deli-status-wcheck" : "Pending Scan Review",
    "deli-status-create" : "New",
    /**brand*/
    "brand-code-exist" : "This code already exists",
    "brand-inv-range-over" : "The second line of inventory must be greater than the first line",
    "brand-inv-range-less" : "The first line of inventory must be less than the second line",
    "brand-number-range" : "Please enter an integer not less than 0",
    /**Store*/
	"store-checkincludingletterstring":"At least one letter",
	"store-modifyformcpd":"Please select the provinces and cities",
	"store-exportstorestring":"Cannot export empty data, please select search criteria again",
	"store-uploadsucess":"Upload Successfully",
	"store-checkuniquestorecode":"This code already exists",
	"store-on":"Enabled",
	"store-off":"Disabled",
	"store-on-succeed":"Enable",
	"store-off-succeed":"Disable",	
	/**return*/
	"return-status-wexpresspick" : "Waiting Delivery",
	"return-status-expresspicked" : "Courier Picked Up",
	"order-total-price": "Total Amount",
	"return-paytime-not-null":" payment time is not null",
	/**pickpackage*/
	"ppackage-status-create" : "Shipping(to Store)",
	"ppackage-status-ssign" : "Received",
	"ppackage-status-csign" : "Customer Picked Up",
	"ppackage-status-expired" : "Unattended Package",	
	"ppackage-status-return" : "Returned",
	"ppackage-status-wreturn" : "Pending Return"
	
});