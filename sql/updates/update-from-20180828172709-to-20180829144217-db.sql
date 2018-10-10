alter table `m_walks` rename to `walks`;
alter table `new_walk_force_groups` rename to `walk_force_groups`;
    
UPDATE `version` SET db_version = '20180829144217';
