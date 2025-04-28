-- MySQL dump 10.13  Distrib 8.4.3, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zzz202308050109
-- ------------------------------------------------------
-- Server version	8.4.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artifacts`
--

DROP TABLE IF EXISTS `artifacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artifacts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `introduce` varchar(100) NOT NULL,
  `provide_hp` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artifacts`
--

INSERT INTO `artifacts` (`id`, `name`, `introduce`, `provide_hp`) VALUES (1,'自由蓝调','异常精通提升30点，强化特殊技命中敌人时，\n根据装备者的属性类型，使目标对应属性异常积蓄抗性降低35%，持续8秒，相同属性的效果不可叠加。',246);
INSERT INTO `artifacts` (`id`, `name`, `introduce`, `provide_hp`) VALUES (2,'折枝剑歌','暴击伤害+16%，异常掌控大于等于115点时，装备者的暴击伤害提升30%；\n队伍中任意角色对敌人施加「冻结」或触发「碎冰」效果时，装备者的暴击率提升12%，持续15秒。',223);
INSERT INTO `artifacts` (`id`, `name`, `introduce`, `provide_hp`) VALUES (3,'震星迪斯科','冲击力提升6%，普通攻击、冲刺攻击、闪避反击，\n对主要目标造成的失衡值提升20%。',210);
INSERT INTO `artifacts` (`id`, `name`, `introduce`, `provide_hp`) VALUES (4,'极地重金属','冰属性伤害提升10%。普通攻击和冲刺攻击造成的伤害提升28%，\n队伍中任意角色对敌人施加「冻结」或触发「碎冰」效果时，该增益额外提升28%，持续12秒。',287);
INSERT INTO `artifacts` (`id`, `name`, `introduce`, `provide_hp`) VALUES (5,'静听嘉音','攻击力+10%，队伍中任意角色通过快速支援入场时，全队获得1层「嘉音」，最多叠加3层，持续15秒，\n重复触发时刷新持续时间，每拥有1层嘉音，通过快速支援入场的角色造成的伤害提升8%，该效果全队唯一。',264);
INSERT INTO `artifacts` (`id`, `name`, `introduce`, `provide_hp`) VALUES (6,'原始朋克','施加的护盾值+15%。队伍中任意角色发动招架支援或回避支援时,\n全队造成的伤害提升15%，持续10秒,同名被动效果之间不可叠加。',320);
INSERT INTO `artifacts` (`id`, `name`, `introduce`, `provide_hp`) VALUES (7,'如影相随','追加攻击或冲刺攻击命中敌人时，若造成的伤害与装备者的属性一致，\n则获得1层增益效果，每拥有1层增益效果，\n装备者的攻击力提升4%，暴击率提升4%，最多叠加3层，持续15秒。\n',296);
INSERT INTO `artifacts` (`id`, `name`, `introduce`, `provide_hp`) VALUES (8,'法厄同之歌','异常掌控+8%。队伍中任意角色发动「强化特殊技」时，装备者的异常精通提升45点，持续8秒；\n如果发动强化特殊技的角色不是装备者本人时，装备者造成的以太伤害提升25%。\n',278);
INSERT INTO `artifacts` (`id`, `name`, `introduce`, `provide_hp`) VALUES (9,'混沌爵士','火属性伤害和电属性伤害提升15%；位于后场时，强化特殊技和支援攻击造成的伤害提升20%，\n换入前场后，该增益效果仍然保留，持续5秒，保留效果7.5秒内最多触发一次。',266);

--
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `weapon_name` varchar(20) NOT NULL,
  `artifacts_name` varchar(20) NOT NULL,
  `introduce` varchar(1000) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `provide_hp` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (1,'莱卡恩','拘缚者','自由蓝调','维多利亚家政的实质领导者兼对外负责人，负责统筹管理所有成员的工作。\n理性又可靠，举止优雅绅士，如同万能执事一般，\n不管发生什么事情都能妥善解决。是团队最坚实的后盾，令人心安的存在。\n有些轻微的洁癖，总会习惯性地把周围清理干净，难以忍受脏污的环境。','laikaen.jpg',8245);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (2,'星见雅','霰落星殿','折枝剑歌','对空洞事务特别行动部第六课课长。\n雅常出外勤，至今不知道部内每月例会的会议室在几楼。\n即使在办公室内看到雅，雅也多半没有在处理文书工作，而是专注于保养佩刀。','xingjianya.png',7673);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (3,'珂蕾妲','燃狱齿轮','震星迪斯科','白祗重工的现任社长。同时也是公司创始人霍尔斯的亲生女儿。\n父亲霍尔斯因涉嫌「卷款潜逃事件」失踪，导致公司遭受巨大冲击，几近一蹶不振。\n在那之后，达到法定年龄的珂蕾妲主动接过烂摊子，整合仅剩的公司人员和资产，重新开始经营资金和声誉都岌岌可危的白祇重工。','keleida.png',8127);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (4,'艾莲','深海访客','极地重金属','维多利亚家政女仆，也是最后一位加入组织的成员。\n节能主义，几乎不喜欢一切需要耗费精力的事情。但在队友们的配合下，可以在关键时刻充分发挥自身的强大战力。\n战斗方式具有高耗能特质，通常需要补充糖分，常把棒棒糖在嘴里。\n虽然嘴上总是说着「麻烦，想换工作」，实际上非常珍惜现在维多利亚家政的队友们。','ailian.jpg',7673);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (5,'耀嘉音','玲珑妆匣','静听嘉音','公认的丽都第一歌姬，凭借其超凡的嗓音、舞台感染力和创作才华，引领了整个时代的音乐风潮。\n如果音乐是灵魂的共鸣，那耀嘉音就是点燃这一切的火花。\n某知名乐评人曾如此评价她。尽管耀眼如斯，嘉音却保持着难得的真诚与纯粹。舞台上，她的歌声直抵人心。\n这种特质，让她得以拥有庞大的忠实粉丝群加饭，当然嘉音的光芒，也离不开经纪人伊芙琳的悉心守护。\n而舞台之下，似乎只是一个在生活上依赖着伊芙琳，在空洞里依赖着哲和铃的可爱女孩。','yaojiayin.png',8609);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (6,'凯撒','奔袭獠牙','原始朋克','卡吕冬之子的领导者，机车组的首领，同时也是一位正在成长中的君王。\n强大的实力和直爽豪放的性格，让她受到外环人们的尊重和喜爱。\n看似我行我素，实际上很愿意听取他人的意见。只要是有效意见，无论是什么人提出的，凯撒都会积极听取并践行。\n对自己相信的人毫无防备。当她承认某个人之后，就会把对方划分到自己的麾下，绝对信任且不设防。\n\n','kaisa.png',9526);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (7,'丽娜','啜泣摇篮','静听嘉音','维多利亚家政的女仆长，同时也是组织资历最深的成员。\n漂亮端庄，从头到脚都非常完美，气质高贵，脸上总是带着淡淡的笑意。\n很在意自己在他人眼中的印象。\n','lina.png',8609);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (8,'安比','牺牲洁纯','如影相随','狡兔屋成立最初的雇员。\n安比·德玛拉这一身份在其加入狡兔屋后才凭空出现，且德玛拉的姓氏明显取自妮可。\n而在以狡免屋雇员的身份进行相关登记之前，安比的个人履历及相关数据全部呈现可疑的空白。\n安比虽然极度缺乏常识，但却精通战斗相关事宜，属于狡免屋最强战力。','anbi.png',7673);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (9,'薇薇安','飞鸟星梦','法厄同之歌','法厄同玩家的迷妹，反舌鸟组织成员。\n薇薇安语录：我的阳伞，只会与法厄同大人共撑。\n真希望法厄同大人的目光只在我身上停留。','weiweian.png',7673);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (10,'扳机','索魂影眸','如影相随','新艾利都防卫军·黑曜石营·奥波勒斯小队的狙击手。\n扳机是其于艾利都时期入伍所持有的代号，一直沿用至今。\n有着极其特殊的双眼，虽然无法看到绝大部分事物，\n但在空洞环境下可以清晰辨别一切具备以太波动事物的方位与变化。\n','banji.png',7923);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (11,'青衣','玉壶青冰','震星迪斯科','刑侦特勤组的新人探员，\n并不是原生人类，其人格意识是基于旧文明的古老文化典籍塑造出来的拟造人格。\n而青衣的身体是基于生物材料制造的智能构造体，俗称钰偶。\n由怀斯塔学会推荐给治安局，被以新人探员的身份配属为朱鸢的搭档。','qingyi.png',8251);
INSERT INTO `characters` (`id`, `name`, `weapon_name`, `artifacts_name`, `introduce`, `image_url`, `provide_hp`) VALUES (12,'月城柳','时流贤者','混沌爵士','对空洞事务特别行动部第六课所属副课长兼情报官。\n主要负责六课的人事管理、任务跟进与反馈、情报收集和现场支援等工作。\n与此同时，月城柳还是六课成员苍角的保护者。\n除了在日常生活上对苍角进行照顾之外，柳还需要对苍角在工作中的一切行为负责。','yuechengliu.png',7688);
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_insert_characters` BEFORE INSERT ON `characters` FOR EACH ROW BEGIN
    DECLARE weapon_exists INT;
    DECLARE artifact_exists INT;
    SELECT COUNT(*) INTO weapon_exists
    FROM weapons
    WHERE name = NEW.weapon_name;
    SELECT COUNT(*) INTO artifact_exists
    FROM artifacts
    WHERE name = NEW.artifacts_name;
    IF weapon_exists = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = '请先添加武器名';
    END IF;
    IF artifact_exists = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = '请先添加驱动盘名';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_update_characters` BEFORE UPDATE ON `characters` FOR EACH ROW BEGIN
    DECLARE weapon_exists INT;
    DECLARE artifact_exists INT;
    SELECT COUNT(*) INTO weapon_exists
    FROM weapons
    WHERE name = NEW.weapon_name;
    SELECT COUNT(*) INTO artifact_exists
    FROM artifacts
    WHERE name = NEW.artifacts_name;
    IF weapon_exists = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = '武器不存在';
    END IF;
    IF artifact_exists = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = '驱动盘不存在';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `weapons`
--

DROP TABLE IF EXISTS `weapons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weapons` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `provide_hp` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weapons`
--

INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (1,'拘缚者','击破',562);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (2,'霰落星殿','异常',580);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (3,'燃狱齿轮','击破',542);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (4,'深海访客','强攻',610);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (5,'玲珑妆匣','支援',555);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (6,'奔袭獠牙','防护',648);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (7,'啜泣摇篮','支援',601);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (8,'牺牲洁纯','强攻',575);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (9,'飞鸟星梦','异常',592);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (10,'索魂影眸','击破',578);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (11,'玉壶青冰','击破',623);
INSERT INTO `weapons` (`id`, `name`, `type`, `provide_hp`) VALUES (12,'时流贤者','异常',588);
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-28 17:17:42
