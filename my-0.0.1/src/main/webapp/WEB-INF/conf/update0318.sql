DROP TABLE IF EXISTS `my`.`special`;
CREATE TABLE  `my`.`special` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `title` varchar(40) NOT NULL,
  `description` varchar(255) default NULL,
  `created` datetime NOT NULL,
  `creator` varchar(80) NOT NULL,
  `modifed` datetime NOT NULL,
  `modifer` varchar(80) NOT NULL,
  `published` tinyint(1) unsigned default '0',
  `hits` int(10) unsigned default '0',
  `logo` varchar(80) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `my`.`special_module`;
CREATE TABLE  `my`.`special_module` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(40) default NULL,
  `pos` int(10) unsigned default '1',
  `block` varchar(40) default NULL,
  `special` int(10) unsigned NOT NULL,
  `type` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `my`.`special_module_list`;
CREATE TABLE  `my`.`special_module_list` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `title` varchar(40) default NULL,
  `href` varchar(128) default NULL,
  `picture` varchar(128) default NULL,
  `created` varchar(10) default NULL,
  `module` int(10) unsigned NOT NULL,
  `pos` int(10) unsigned default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `my`.`special_module_body`;
CREATE TABLE  `my`.`special_module_body` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `content` text,
  `module` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `my`.`special_style`;
CREATE TABLE  `my`.`special_style` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `special` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;