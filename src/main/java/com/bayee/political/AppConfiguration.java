package com.bayee.political;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bayee.political.configuration.DispatcherConfig;
import com.bayee.political.configuration.ThymeleafConfig;
import com.bayee.political.mapper.*;

@Configuration
@EnableWebMvc
@PropertySource({ "classpath:db.properties", "classpath:config.properties", "classpath:mail.properties",
		"classpath:app.properties" })
@ComponentScan(basePackages = "com.bayee.political")
@Import({ WebInitializer.class, DispatcherConfig.class, ThymeleafConfig.class })
public class AppConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setDefaultAutoCommit(true);
		return dataSource;
	}

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(50);
		return taskScheduler;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(6000000); // 6mb
		return commonsMultipartResolver;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		//sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sqlSessionFactory.getObject().getConfiguration().addMapper(DepartmentMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(HomePageMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveProcessCodeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveProcessMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveProcessTaskMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveProcessOperationRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(UserEvaluationMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(UserMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(UserRewardMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(CalculationMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(CalculationProjectMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(PolicePositionMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateAuthorityMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateParticipantMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateRankMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateRankDetailMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateTaskMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateTemplateMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateAuthorityTargetMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateTaskParticipantMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(GroupManagementMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateTargetMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(PoliceStationMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(PoliceStationPostMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(CalculationDetailMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(FeedbackMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(FeedbackTypeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(QuestionnaireMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(QuestionnaireAnswerMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(QuestionnaireOptionMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateProjectMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(EvaluateTaskParticipantTempMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(CalculationPolicePostMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(CalculationPoliceStationMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(ClockRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmTalkMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmEvaluationMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmPoliceMonthMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmScoringBreakdownMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmScoringSonBreakdownMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmScoreItemMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmTypeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmTalkPowerMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(NotificationMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveRestManageMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveIntegralManageMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeavePowerMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveTrainMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveAnnualMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveRestAlarmRuleMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveOvertimeRuleMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveIntegralExchangeRuleMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveCompensatoryRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeavePointsExchangeRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveOvertimeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeavePointsMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveDutyMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveCompensatoryAlarmMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainPhysicalMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainPhysicalAchievementMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainGroupMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainFirearmAchievementMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainFirearmMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainMatchMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainProjectMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainScorerMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainProjectRuleMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainMedalManageMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainPhysicalAchievementDetailsMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainActivityStyleMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainConstitutionMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainPacesetterMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainMatchTypeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainUnitMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainPhysicalProjectRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainProjectURuleMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainLeaderMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainMatchProjectMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainMatchAchievementMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainMatchDepartmentAchievementMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainEvaluateRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainLikeRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainGetMedalMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeavePowerObjectMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(TrainMatchBestAchievementMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(DrinkRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(CalculationAlarmMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(CalculationFactorMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmEntryAndExitRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(LeaveCompensatoryReadRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(AlarmUrgeRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskHealthMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskHealthBmiStandardMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskHealthRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskAlarmMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskAlarmTypeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskReportRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskTrainMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskDutyMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskDutyDealPoliceRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskCaseMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskCaseAbilityRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskCaseLawEnforcementRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskCaseLawEnforcementTypeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskCaseTestRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskCaseLawEnforcementMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskCaseAbilityMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskFamilyEvaluationMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskFamilyEvaluationRankMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskFamilyEvaluationRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskConductMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskConductBureauRuleRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskConductTrafficViolationMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskConductTrafficViolationRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskSocialContactMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskSocialContactRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskConductBureauRuleMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskConductVisitMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskConductVisitRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskDrinkRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskStutyUnitTrainRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskStutyActivitiesPartyRecordMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskConductVisitTypeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskCaseTestMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskConductBureauRuleTypeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskHealthRecordInfoMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskTrendsMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(MeasuresMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskDutyErrorTypeMapper.class);
		sqlSessionFactory.getObject().getConfiguration().addMapper(RiskDutyInformationTypeMapper.class);
		return sqlSessionFactory.getObject();
	}

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("mail.host"));
		mailSender.setUsername(env.getProperty("mail.username"));
		mailSender.setPassword(env.getProperty("mail.password"));
		mailSender.setPort(Integer.valueOf(env.getProperty("mail.port")));
		mailSender.setProtocol("smtp");
		Properties javaMailProperties = System.getProperties();
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.socketFactory.port", "25");
		javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException {
		VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
		Map<String, Object> velocityPropertiesMap = new HashMap<String, Object>();
		velocityPropertiesMap.put("resource.loader", "class");
		velocityPropertiesMap.put("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		velocityEngine.setVelocityPropertiesMap(velocityPropertiesMap);
		return velocityEngine.createVelocityEngine();
	}

	@Bean
	public RiskDutyInformationTypeMapper riskDutyInformationTypeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskDutyInformationTypeMapper.class);
	}

	@Bean
	public RiskDutyErrorTypeMapper riskDutyErrorTypeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskDutyErrorTypeMapper.class);
	}

	@Bean
	public MeasuresMapper measuresMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(MeasuresMapper.class);
	}

	@Bean
	public RiskHealthRecordInfoMapper riskHealthRecordInfoMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskHealthRecordInfoMapper.class);
	}

	@Bean
	public RiskConductBureauRuleTypeMapper riskConductBureauRuleTypeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskConductBureauRuleTypeMapper.class);
	}

	@Bean
	public RiskConductBureauRuleMapper riskConductBureauRuleMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskConductBureauRuleMapper.class);
	}

	@Bean
	public RiskCaseTestMapper riskCaseTestMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskCaseTestMapper.class);
	}

	@Bean
	public RiskConductMapper riskConductMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskConductMapper.class);
	}

	@Bean
	public DepartmentMapper deparmentMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(DepartmentMapper.class);
	}

	@Bean
	public UserMapper userMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(UserMapper.class);
	}

	@Bean
	public LeaveProcessCodeMapper leaveProcessCodeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveProcessCodeMapper.class);
	}

	@Bean
	public LeaveProcessMapper LeaveProcessMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveProcessMapper.class);
	}

	@Bean
	public LeaveProcessTaskMapper leaveProcessTaskMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveProcessTaskMapper.class);
	}

	@Bean
	public LeaveProcessOperationRecordMapper leaveProcessOperationRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveProcessOperationRecordMapper.class);
	}

	@Bean
	public UserEvaluationMapper userEvaluationMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(UserEvaluationMapper.class);
	}

	@Bean
	public UserRewardMapper userRewardMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(UserRewardMapper.class);
	}

	@Bean
	public CalculationMapper calculationMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(CalculationMapper.class);
	}

	@Bean
	public CalculationProjectMapper calculationProjectMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(CalculationProjectMapper.class);
	}

	@Bean
	public PolicePositionMapper policePositionMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(PolicePositionMapper.class);
	}

	@Bean
	public EvaluateAuthorityMapper evaluateAuthorityMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateAuthorityMapper.class);
	}

	@Bean
	public EvaluateParticipantMapper evaluateParticipantMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateParticipantMapper.class);
	}

	@Bean
	public EvaluateAuthorityTargetMapper evaluateAuthorityTargetMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateAuthorityTargetMapper.class);
	}

	@Bean
	public EvaluateTaskParticipantMapper evaluateTaskParticipantMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateTaskParticipantMapper.class);
	}

	@Bean
	public EvaluateRankMapper evaluateRankMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateRankMapper.class);
	}

	@Bean
	public EvaluateTaskMapper evaluateTaskMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateTaskMapper.class);
	}

	@Bean
	public EvaluateTemplateMapper evaluateTemplateMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateTemplateMapper.class);
	}

	@Bean
	public GroupManagementMapper groupManagementMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(GroupManagementMapper.class);
	}

	@Bean
	public EvaluateTargetMapper evaluateTargetMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateTargetMapper.class);
	}

	@Bean
	public EvaluateRankDetailMapper evaluateRankDetailMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateRankDetailMapper.class);
	}

	@Bean
	public PoliceStationMapper policeStationMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(PoliceStationMapper.class);
	}

	@Bean
	public PoliceStationPostMapper policeStationPostMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(PoliceStationPostMapper.class);
	}

	@Bean
	public CalculationDetailMapper calculationDetailMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(CalculationDetailMapper.class);
	}

	@Bean
	public FeedbackMapper feedbackMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(FeedbackMapper.class);
	}

	@Bean
	public FeedbackTypeMapper feedbackTypeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(FeedbackTypeMapper.class);
	}

	@Bean
	public QuestionnaireMapper questionnaireMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(QuestionnaireMapper.class);
	}

	@Bean
	public QuestionnaireAnswerMapper questionnaireAnswerMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(QuestionnaireAnswerMapper.class);
	}

	@Bean
	public QuestionnaireOptionMapper questionnaireOptionMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(QuestionnaireOptionMapper.class);
	}

	@Bean
	public EvaluateProjectMapper evaluateProjectMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateProjectMapper.class);
	}

	@Bean
	public EvaluateTaskParticipantTempMapper evaluateTaskParticipantTempMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(EvaluateTaskParticipantTempMapper.class);
	}

	@Bean
	public CalculationPolicePostMapper calculationPolicePostMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(CalculationPolicePostMapper.class);
	}

	@Bean
	public CalculationPoliceStationMapper calculationPoliceStationMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(CalculationPoliceStationMapper.class);
	}

	@Bean
	public ClockRecordMapper clockRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(ClockRecordMapper.class);
	}

	@Bean
	public AlarmTalkMapper alarmTalkMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmTalkMapper.class);
	}

	@Bean
	public AlarmEvaluationMapper alarmEvaluationMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmEvaluationMapper.class);
	}

	@Bean
	public AlarmPoliceMonthMapper alarmPoliceMonthMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmPoliceMonthMapper.class);
	}

	@Bean
	public AlarmScoringBreakdownMapper alarmScoringBreakdownMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmScoringBreakdownMapper.class);
	}

	@Bean
	public AlarmScoringSonBreakdownMapper alarmScoringSonBreakdownMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmScoringSonBreakdownMapper.class);
	}

	@Bean
	public AlarmScoreItemMapper alarmScoreItemMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmScoreItemMapper.class);
	}

	@Bean
	public AlarmTypeMapper alarmTypeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmTypeMapper.class);
	}

	@Bean
	public AlarmRecordMapper alarmRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmRecordMapper.class);
	}

	@Bean
	public AlarmTalkPowerMapper alarmTalkPowerMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmTalkPowerMapper.class);
	}

	@Bean
	public NotificationMapper notificationMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(NotificationMapper.class);
	}

	@Bean
	public LeaveRestManageMapper leaveRestManageMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveRestManageMapper.class);
	}

	@Bean
	public LeavePowerMapper leavePowerMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeavePowerMapper.class);
	}

	@Bean
	public LeaveIntegralManageMapper leaveIntegralManageMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveIntegralManageMapper.class);
	}

	@Bean
	public LeaveAnnualMapper leaveAnnualMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveAnnualMapper.class);
	}

	@Bean
	public LeaveTrainMapper leaveTrainMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveTrainMapper.class);
	}

	@Bean
	public LeaveRestAlarmRuleMapper leaveRestAlarmRuleMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveRestAlarmRuleMapper.class);
	}

	@Bean
	public LeaveOvertimeRuleMapper leaveOvertimeRuleMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveOvertimeRuleMapper.class);
	}

	@Bean
	public LeaveIntegralExchangeRuleMapper leaveIntegralExchangeRuleMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveIntegralExchangeRuleMapper.class);
	}

	@Bean
	public LeaveCompensatoryRecordMapper leaveCompensatoryRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveCompensatoryRecordMapper.class);
	}

	@Bean
	public LeavePointsExchangeRecordMapper leavePointsExchangeRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeavePointsExchangeRecordMapper.class);
	}

	@Bean
	public LeaveOvertimeMapper leaveOvertimeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveOvertimeMapper.class);
	}

	@Bean
	public LeavePointsMapper leavePointsMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeavePointsMapper.class);
	}

	@Bean
	public LeaveDutyMapper leaveDutyMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveDutyMapper.class);
	}

	@Bean
	public LeaveCompensatoryAlarmMapper leaveCompensatoryAlarmMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveCompensatoryAlarmMapper.class);
	}

	@Bean
	public TrainPhysicalAchievementMapper trainPhysicalAchievementMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainPhysicalAchievementMapper.class);
	}

	@Bean
	public TrainPhysicalMapper TrainPhysicalMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainPhysicalMapper.class);
	}
	
	@Bean
	public TrainGroupMapper trainGroupMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainGroupMapper.class);
	}
	
	@Bean
	public TrainFirearmAchievementMapper trainFirearmAchievementMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainFirearmAchievementMapper.class);
	}
	
	@Bean
	public TrainFirearmMapper trainFirearmMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainFirearmMapper.class);
	}
	
	@Bean
	public TrainMatchMapper trainMatchMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainMatchMapper.class);
	}
	
	@Bean
	public TrainProjectMapper trainProjectMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainProjectMapper.class);
	}
	
	@Bean
	public TrainScorerMapper trainScorerMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainScorerMapper.class);
	}
	
	@Bean
	public TrainProjectRuleMapper trainProjectRuleMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainProjectRuleMapper.class);
	}
	
	@Bean
	public TrainMedalManageMapper trainMedalManageMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainMedalManageMapper.class);
	}
	
	@Bean
	public TrainPhysicalAchievementDetailsMapper trainPhysicalAchievementDetailsMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainPhysicalAchievementDetailsMapper.class);
	}
	
	@Bean
	public TrainActivityStyleMapper trainActivityStyleMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainActivityStyleMapper.class);
	}
	
	@Bean
	public TrainConstitutionMapper trainConstitutionMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainConstitutionMapper.class);
	}
	
	@Bean
	public TrainPacesetterMapper trainPacesetterMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainPacesetterMapper.class);
	}
	
	@Bean
	public TrainMatchTypeMapper trainMatchTypeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainMatchTypeMapper.class);
	}
	
	@Bean
	public TrainUnitMapper trainUnitMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainUnitMapper.class);
	}
	
	@Bean
	public TrainPhysicalProjectRecordMapper trainPhysicalProjectRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainPhysicalProjectRecordMapper.class);
	}
	
	@Bean
	public TrainProjectURuleMapper trainProjectURuleMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainProjectURuleMapper.class);
	}
	
	@Bean
	public TrainLeaderMapper trainLeaderMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainLeaderMapper.class);
	}
	
	@Bean
	public TrainMatchProjectMapper trainMatchProjectMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainMatchProjectMapper.class);
	}
	
	@Bean
	public TrainMatchAchievementMapper trainMatchAchievementMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainMatchAchievementMapper.class);
	}
	
	@Bean
	public TrainMatchDepartmentAchievementMapper trainMatchDepartmentAchievementMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainMatchDepartmentAchievementMapper.class);
	}
	
	@Bean
	public TrainEvaluateRecordMapper trainEvaluateRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainEvaluateRecordMapper.class);
	}
	
	@Bean
	public TrainLikeRecordMapper trainLikeRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainLikeRecordMapper.class);
	}
	
	@Bean
	public TrainGetMedalMapper trainGetMedalMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainGetMedalMapper.class);
	}
	
	@Bean
	public LeavePowerObjectMapper leavePowerObjectMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeavePowerObjectMapper.class);
	}
	
	@Bean
	public TrainMatchBestAchievementMapper trainMatchBestAchievementMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(TrainMatchBestAchievementMapper.class);
	}
	
	@Bean
	public DrinkRecordMapper drinkRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(DrinkRecordMapper.class);
	}
	
	@Bean
	public CalculationAlarmMapper calculationAlarmMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(CalculationAlarmMapper.class);
	}
	
	@Bean
	public CalculationFactorMapper calculationFactorMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(CalculationFactorMapper.class);
	}
	
	@Bean
	public AlarmEntryAndExitRecordMapper alarmEntryAndExitRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmEntryAndExitRecordMapper.class);
	}
	
	@Bean
	public LeaveCompensatoryReadRecordMapper leaveCompensatoryReadRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(LeaveCompensatoryReadRecordMapper.class);
	}
	
	@Bean
	public AlarmUrgeRecordMapper alarmUrgeRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(AlarmUrgeRecordMapper.class);
	}
	
	@Bean
	public HomePageMapper homePageMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(HomePageMapper.class);
	}
	
	@Bean
	public RiskHealthRecordMapper riskHealthRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskHealthRecordMapper.class);
	}
	
	@Bean
	public RiskHealthBmiStandardMapper riskHealthBmiStandardMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskHealthBmiStandardMapper.class);
	}
	
	@Bean
	public RiskHealthMapper riskHealthMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskHealthMapper.class);
	}
	
	@Bean
	public RiskAlarmTypeMapper riskAlarmTypeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskAlarmTypeMapper.class);
	}
	
	@Bean
	public RiskAlarmMapper riskAlarmMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskAlarmMapper.class);
	}
	
	@Bean
	public RiskReportRecordMapper riskReportRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskReportRecordMapper.class);
	}
	
	@Bean
	public RiskTrainMapper riskTrainMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskTrainMapper.class);
	}
	
	@Bean
	public RiskDutyMapper riskDutyMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskDutyMapper.class);
	}
	
	@Bean
	public RiskDutyDealPoliceRecordMapper riskDutyDealPoliceRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskDutyDealPoliceRecordMapper.class);
	}
	
	@Bean
	public RiskCaseMapper riskCaseMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskCaseMapper.class);
	}
	
	@Bean
	public RiskCaseAbilityRecordMapper riskCaseAbilityRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskCaseAbilityRecordMapper.class);
	}
	
	@Bean
	public RiskCaseLawEnforcementRecordMapper riskCaseLawEnforcementRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskCaseLawEnforcementRecordMapper.class);
	}
	
	@Bean
	public RiskCaseLawEnforcementTypeMapper riskCaseLawEnforcementTypeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskCaseLawEnforcementTypeMapper.class);
	}
	
	@Bean
	public RiskCaseTestRecordMapper riskCaseTestRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskCaseTestRecordMapper.class);
	}
	
	@Bean
	public RiskCaseLawEnforcementMapper riskCaseLawEnforcementMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskCaseLawEnforcementMapper.class);
	}
	
	@Bean
	public RiskCaseAbilityMapper riskCaseAbilityMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskCaseAbilityMapper.class);
	}
	
	@Bean
	public RiskFamilyEvaluationMapper riskFamilyEvaluationMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskFamilyEvaluationMapper.class);
	}
	
	@Bean
	public RiskFamilyEvaluationRankMapper riskFamilyEvaluationRankMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskFamilyEvaluationRankMapper.class);
	}
	
	@Bean
	public RiskFamilyEvaluationRecordMapper riskFamilyEvaluationRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskFamilyEvaluationRecordMapper.class);
	}
	
	@Bean
	public RiskConductBureauRuleRecordMapper riskConductBureauRuleRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskConductBureauRuleRecordMapper.class);
	}
	
	@Bean
	public RiskConductTrafficViolationMapper riskConductTrafficViolationMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskConductTrafficViolationMapper.class);
	}
	
	@Bean
	public RiskConductTrafficViolationRecordMapper riskConductTrafficViolationRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskConductTrafficViolationRecordMapper.class);
	}
	
	@Bean
	public RiskSocialContactMapper riskSocialContactMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskSocialContactMapper.class);
	}
	
	@Bean
	public RiskSocialContactRecordMapper riskSocialContactRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskSocialContactRecordMapper.class);
	}
	
	@Bean
	public RiskConductVisitMapper riskConductVisitMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskConductVisitMapper.class);
	}
	
	@Bean
	public RiskConductVisitRecordMapper riskConductVisitRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskConductVisitRecordMapper.class);
	}
	
	@Bean
	public RiskDrinkRecordMapper riskDrinkRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskDrinkRecordMapper.class);
	}
	
	@Bean
	public RiskStutyUnitTrainRecordMapper riskStutyUnitTrainRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskStutyUnitTrainRecordMapper.class);
	}
	
	@Bean
	public RiskStutyActivitiesPartyRecordMapper riskStutyActivitiesPartyRecordMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskStutyActivitiesPartyRecordMapper.class);
	}
	
	@Bean
	public RiskConductVisitTypeMapper riskConductVisitTypeMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskConductVisitTypeMapper.class);
	}
	
	@Bean
	public RiskTrendsMapper riskTrendsMapper() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sessionTemplate.getMapper(RiskTrendsMapper.class);
	}
}
