ALTER TABLE `my`.`special_module_list` DROP COLUMN `title`,
 DROP COLUMN `href`,
 DROP COLUMN `picture`,
 ADD COLUMN `objectId` INTEGER UNSIGNED AFTER `pos`,
 ADD COLUMN `objectType` INTEGER UNSIGNED AFTER `objectId`;
 
 DROP TABLE IF EXISTS `my`.`special_link`;
CREATE TABLE  `my`.`special_link` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `title` varchar(80) default NULL,
  `href` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `special` int(10) unsigned default NULL,
  `created` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

ALTER TABLE `my`.`special_module` ADD COLUMN `parent` INTEGER;
