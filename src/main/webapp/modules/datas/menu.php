<?php 

$top_menu =array(
         array('title'=>'系统管理','icon'=>'larry-xitongshezhi1','pid'=>'0'),
         array('title'=>'内容管理','icon'=>'larry-neirongguanli','pid'=>'35'),
         array('title'=>'微信公众','icon'=>'larry-weixingongzhongpingtai','pid'=>'40'),
         array('title'=>'扩展模块','icon'=>'larry-ht_expand','pid'=>'46')
	);
$pidone = array(
        array('title'=>'后台首页','icon'=>'larry-houtaishouye','href'=>'html/main.php'),
	  array(
      	 'title'=>'我的面板',
      	 'icon'=>'larry-gerenxinxi5',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'个人信息','icon'=>'larry-gerenxinxi1','href'=>'html/personInfo.html'),
             array('title'=>'修改密码','icon'=>'larry-xiugaimima2','href'=>'html/changepwd.html'),
             array('title'=>'日志信息','icon'=>'larry-rizhi2','href'=>'html/myloginfo.html')
      	)),
        array(
      	 'title'=>'用户管理',
      	 'icon'=>'larry-10103',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'用户列表','icon'=>'larry-yonghuliebiao1','href'=>'html/main.php'),
             array('title'=>'角色列表','icon'=>'larry-jiaoseguanli1','href'=>'html/temp.html'),
             array('title'=>'菜单管理','icon'=>'larry-caidanguanli','href'=>'html/temp.html')
      	)),
        array(
      	 'title'=>'会员管理',
      	 'icon'=>'larry-huiyuanguanli2',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'会员注册列表','icon'=>'larry-kehuliebiao','href'=>'html/temp.html'),
             array('title'=>'会员留言管理','icon'=>'larry-liuyan','href'=>'html/temp.html'),
             array('title'=>'会员等级管理','icon'=>'larry-fengxianbaozhang','href'=>'html/temp.html')
      	)),
      	array(
      	 'title'=>'系统设置',
      	 'icon'=>'larry-xitongshezhi1',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'系统基本参数','icon'=>'larry-circularxiangxi','href'=>'html/temp.html'),
             array('title'=>'SQL命令行工具','icon'=>'larry-sql2','href'=>'html/temp.html'),
             array('title'=>'系统日志管理','icon'=>'larry-xitongrizhi1','href'=>'html/temp.html'),
             array('title'=>'验证安全管理','icon'=>'larry-yanzheng','href'=>'html/temp.html'),
             array('title'=>'图片水印设置','icon'=>'larry-shuiyin','href'=>'html/temp.html'),
             array('title'=>'安全字典设置','icon'=>'larry-zidian','href'=>'html/temp.html'),
             array('title'=>'短信接口配置','icon'=>'larry-duanxin1','href'=>'html/temp.html'),
             array('title'=>'系统错误修复','icon'=>'larry-xiufu','href'=>'html/temp.html'),
             array('title'=>'数据库管理','icon'=>'larry-sql1','href'=>'html/temp.html')
      	)),
      	array(
      	 'title'=>'友链管理',
      	 'icon'=>'larry-a157',
      	 'href'=>'html/temp.html'
      	),
);
$pid35 = array(
       array(
      	 'title'=>'网站管理',
      	 'icon'=>'larry-30wangzhanguanli',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'网站栏目管理','icon'=>'larry-lanmuguanli','href'=>'html/temp.html'),
             array('title'=>'所有文章列表','icon'=>'larry-iconfontfilesfill','href'=>'html/temp.html'),
             array('title'=>'待审核的文章','icon'=>'larry-daishenhe1','href'=>'html/temp.html'),
             array('title'=>'我发布的文章','icon'=>'larry-fabu2','href'=>'html/temp.html'),
             array('title'=>'Tags管理','icon'=>'larry-tags','href'=>'html/temp.html'),
             array('title'=>'内容回收站','icon'=>'larry-iconfonthuishouzhan','href'=>'html/temp.html')
      	)),
       array(
      	 'title'=>'模型管理',
      	 'icon'=>'larry-moxingguanli',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'内容模型管理','icon'=>'larry-moxing1','href'=>'html/temp.html'),
             array('title'=>'单页文档管理','icon'=>'larry-danye','href'=>'html/temp.html'),
             array('title'=>'联动类别管理','icon'=>'larry-liandong','href'=>'html/temp.html'),
             array('title'=>'自由列表管理','icon'=>'larry-zidingyicaidan1','href'=>'html/temp.html'),
             array('title'=>'自定义表单','icon'=>'larry-iconzidingyibiaodan','href'=>'html/temp.html')
      	)),
       array(
      	 'title'=>'附件管理',
      	 'icon'=>'larry-fujianguanli',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'上传新文件','icon'=>'larry-shangchuanfujian','href'=>'html/temp.html'),
             array('title'=>'附件数据管理','icon'=>'larry-fujian','href'=>'html/temp.html'),
             array('title'=>'文件管理器','icon'=>'larry-wenjianguanli2','href'=>'html/temp.html')
      	)),
       array(
      	 'title'=>'采集管理',
      	 'icon'=>'larry-eee',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'采集节点管理','icon'=>'larry-shujucaiji','href'=>'html/temp.html'),
             array('title'=>'临时内容管理','icon'=>'larry-linshi1','href'=>'html/temp.html'),
             array('title'=>'导入采集规则','icon'=>'larry-guize','href'=>'html/temp.html'),
             array('title'=>'监控采集模式','icon'=>'larry-jiankong1','href'=>'html/temp.html'),
             array('title'=>'采集未下载内容','icon'=>'larry-xiazai2','href'=>'html/temp.html')
      	)),
      	array(
      	 'title'=>'广告管理',
      	 'icon'=>'larry-iconguanggaoguanli',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'自定义广告','icon'=>'larry-zidingyiguanggaolan','href'=>'html/temp.html'),
             array('title'=>'网盟广告','icon'=>'larry-guanggaolianmeng','href'=>'html/temp.html')
      	)),
      	array(
      	 'title'=>'批量维护',
      	 'icon'=>'larry-piliang',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'一键更新网站','icon'=>'larry-yijian','href'=>'html/temp.html'),
             array('title'=>'更新系统缓存','icon'=>'larry-xitonghuancun','href'=>'html/temp.html'),
             array('title'=>'更新主页HTML','icon'=>'larry-tubiaozitimoban','href'=>'html/temp.html'),
             array('title'=>'更新栏目HTML','icon'=>'larry-lanmu1','href'=>'html/temp.html'),
             array('title'=>'更新文档HTML','icon'=>'larry-wendang','href'=>'html/temp.html'),
             array('title'=>'更新网站地图','icon'=>'larry-wangzhanditu','href'=>'html/temp.html'),
             array('title'=>'重复文档检测','icon'=>'larry-zhongfu','href'=>'html/temp.html'),
             array('title'=>'数据库内容替换','icon'=>'larry-tihuan1','href'=>'html/temp.html'),
             array('title'=>'自动摘要','icon'=>'larry-zhaiyao','href'=>'html/temp.html'),
             array('title'=>'搜索关键词维护','icon'=>'larry-guanjianci','href'=>'html/temp.html')
      	))
);
$pid40 = array(
       array(
      	 'title'=>'微信配置',
      	 'icon'=>'larry-weixinguanli',
      	 'spread'=>'true',
      	 'children'=>array(
             array('title'=>'微信接口设置','icon'=>'larry-api1','href'=>'html/temp.html'),
             array('title'=>'微信支付配置','icon'=>'larry-iconzfb','href'=>'html/temp.html')
      	))
);
$pid46 = array(
       array(
      	 'title'=>'支付宝支付配置',
      	 'icon'=>'larry-zhifubao',
      	 'href'=>'html/temp.html'
      	)
);
if(!isset($_GET['pid']) && $_GET['Param'] == 'index_menu'){
      echo json_encode($top_menu);
}else if($_GET['pid'] == '0' && $_GET['Param'] == 'index_menu'){
      echo json_encode($pidone);
}else if($_GET['pid'] == '35' && $_GET['Param'] == 'index_menu'){
      echo json_encode($pid35);
}else if($_GET['pid'] == '40' && $_GET['Param'] == 'index_menu'){
      echo json_encode($pid40);
}else if($_GET['pid'] == '46' && $_GET['Param'] == 'index_menu'){
      echo json_encode($pid46);
}