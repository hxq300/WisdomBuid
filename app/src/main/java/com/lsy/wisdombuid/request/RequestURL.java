package com.lsy.wisdombuid.request;

/**
 * Create by lsy on 2019/09/19
 * MODO :
 */
public class RequestURL {
    // 正式服
    public static String RequestUrl = "http://192.168.1.103:8891";
    // 测试服
//    public static String RequestUrl = "http://101.132.154.10:8891";

    public static String RequestImg = "http://101.132.154.10:8891/img/download_img?img_url=";
    public static String OssUrl = "http://jjjt.oss-cn-shanghai.aliyuncs.com/";

    //Login部分
    public static String login = RequestUrl + "/login/LoginApp?";//管理人员登录
    public static String loginLabor = RequestUrl + "/login/LoginApp1?";//劳务人员登录（未使用）
    public static String getCode = RequestUrl + "/code/gain_code?";//获取验证码
    public static String forgetPass = RequestUrl + "/staff/ForgetPassword?";//忘记、修改密码

    public static String homeTab = RequestUrl + "/title/FindById?";//首页按钮数据

    public static String personCount = RequestUrl + "/count_App/PersonCount?";//人员管理
    public static String selectStation = RequestUrl + "/station/SelectStation?";//下拉框查询站点信息(站点  固定)

    // 物料检测
    public static String materialMonitoringSystem = RequestUrl+ "/Environment/FindEnvironment?";// 查询最近一条扬尘检测仪数据
    public static String weatherUrl = "https://www.tianqiapi.com/api?version=v1&appid=53622763&appsecret=NFgVUwV2&cityid=101181410";// 一周天气详情


    //安全管理系统
//    传参:title(标题),risk_id(安全隐患类id,安全隐患类型下拉框), staff_name(员工姓名,登录时返回), section_id(标段id), station_id(站点id,下拉框查询),sub_id(分包单位id) description(详细说明), url(图片地址数组), staff_id(劳务人员id,管理人员不需要传,登录返回), process_id(工序id,下拉框返回)
    public static String insertRiskshow = RequestUrl + "/riskshow/InsertRiskshow?";//增加安全隐患记录（隐患上报）
    //    public static String safetyZGL = RequestUrl + "/count_App/CountRiskShowByZGL?";//安全隐患整改率
    public static String safetyZGL = RequestUrl + "/count_App/CountRiskshow?";//安全质量隐患数据

    public static String safetyInspectionRecord = RequestUrl + "/riskshow/FindRiskshow_JCJL?";//检查记录 。
    public static String safetyInvalidRecords = RequestUrl + "/riskshow/FindRiskshow_WXJL?";//无效记录
    public static String safetyAudit = RequestUrl + "/riskshow/UpdateActive?";//审核安全隐患记录（检查记录）
    public static String safetyAfterRectification = RequestUrl + "/riskshow/FindRiskshow_DZG?";//待整改
    public static String safetyRectificationReport = RequestUrl + "/riskshow/UpdateZgUrl?";//整改上报
    public static String findDataZgUrl = RequestUrl + "/riskshow/FindRiskshow_ZGFC?";//整改复查（列表）。
    public static String updateStrtus = RequestUrl + "/riskshow/UpdateStatus?";//整改复查（上报）
    public static String selectRisk = RequestUrl + "/risk/SelectRisk?";//下拉框查询安全隐患类型

    //我的部分
    public static String perInformation = RequestUrl + "/staff/FindStaffById?";//个人资料中，员工信息
    public static String updateInformation = RequestUrl + "/staff/UpdateStaffApp?";//修改个人资料（头像，昵称）
    public static String myMessage = RequestUrl + "/message/FindMessage?";//我的消息

    //积分商城
    public static String findCommodity = RequestUrl + "/commodity/FindCommodity?";//分页模糊查询商品id
    public static String insertConversions = RequestUrl + "/conversion/InsertConversions?";//增加兑换记录
    public static String findConversions = RequestUrl + "/conversion/FindConversionsByStaffId?";//历史个人兑换记录
    public static String findAnintegral = RequestUrl + "/anintegral/FindAnintegral?";//个人积分明细接口

    //培训考核
//    public static String trainMenu = RequestUrl + "/train/Train_Menu?";//培训课程分类列表
    public static String findTrainAll = RequestUrl + "/train/FindTrainAll?";//培训课程分类列表
    public static String findTrainByIdX = RequestUrl + "/train/FindTrainByIdX?";//根据id查询培训内容

    //质量隐患
    public static String countQualityshow = RequestUrl + "/count_App/CountQualityshow?";//质量隐患数据
    public static String findQualityshow_JCJL = RequestUrl + "/qualityshow/FindQualityshow_JCJL?";//质量检查记录
    public static String updateActive = RequestUrl + "/qualityshow/UpdateActive?";//审核质量检查记录
    public static String findQualityshow_WXJL = RequestUrl + "/qualityshow/FindQualityshow_WXJL?";//质量无效记录
    public static String findQualityshow_DZG = RequestUrl + "/qualityshow/FindQualityshow_DZG?";//待整改
    public static String updateZgUrl = RequestUrl + "/qualityshow/UpdateZgUrl?";//整改上报
    public static String findQualityshow_ZGFC = RequestUrl + "/qualityshow/FindQualityshow_ZGFC?";//整改复查(列表)
    public static String updateStatus = RequestUrl + "/qualityshow/UpdateStatus?";//整改复查
    public static String selectQuality = RequestUrl + "/quality/SelectQuality?";//下拉框查询质量隐患类型

    //    传参:title(标题),quality_id(安全隐患类id,安全隐患类型下拉框), staff_name(员工姓名,登录时返回), section_id(标段id), station_id(站点id,下拉框查询),sub_id(分包单位id) description(详细说明), url1(图片地址数组), staff_id(劳务人员id,管理人员不需要传,登录返回), process_id(工序id,下拉框返回),responsible(责任人)
    public static String insertQualityshow = RequestUrl + "/qualityshow/InsertQualityshow?";//增加质量隐患记录

    //拍照上传
    public static String selectProcess = RequestUrl + "/process/SelectProcess?";//下拉查询工序
    public static String selectSubcontractors = RequestUrl + "/subcontractors/SelectSubcontractors?";//下拉框查询分包单位信息

    //======培训考核
    public static String findExamNew = RequestUrl + "/exam/FindExamNew?";//查询最新考试（App）
    public static String findExaminationById = RequestUrl + "/examination/FindExaminationById?";//根据id查询试卷    传参：examination_id（考试接口返回
    public static String findQuestionsById = RequestUrl + "/questions/FindQuestionsById?";//查询题目    传参：id （试卷返回的题目数组）
    public static String insertExamRecord = RequestUrl + "/examRecord/InsertExamRecord?";//增加考试记录
    public static String findExamRecordByStaffId = RequestUrl + "/examRecord/FindExamRecordByStaffId?";//考试记录
    //    public static String findExamination = RequestUrl + "/examination/FindExamination?";//分页查询试卷
    public static String findExamination = RequestUrl + "/exam/FindExam?";//分页查询试卷


    //==========扫码培训部分
    public static String findTrainContent = RequestUrl + "/traincontent/FindTrainContent?";//根据id查询培训内容
    public static String findALL = RequestUrl + "/CodeS/FindALL?";//根据id查询培训内容
    public static String insertTrainRecord = RequestUrl + "/TrainRecord/InsertTrainRecord?";//提交项目体验内容
    //    public static String findTrainRecord = RequestUrl + "/TrainRecord/FindTrainRecord?";//项目体验记录
    public static String findTrainContentAll = RequestUrl + "/traincontent/FindTrainContentAll?";//项目体验记录
    public static String findTrainRecordByTrainName = RequestUrl + "/TrainRecord/FindTrainRecordByTrainName?";//体验项目详情 及 体验人员信息


}
