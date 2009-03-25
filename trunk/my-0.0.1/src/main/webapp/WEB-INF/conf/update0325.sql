ALTER TABLE `my`.`special_article_part` ADD COLUMN `version` VARCHAR(20);
ALTER TABLE `my`.`article_part` ADD COLUMN `version` VARCHAR(20);
DROP TABLE IF EXISTS `my`.`user_config`;
CREATE TABLE  `my`.`user_config` (
  `username` varchar(80) NOT NULL,
  `content` varchar(2000) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;