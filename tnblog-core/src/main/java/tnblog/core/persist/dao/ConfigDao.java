/*
+--------------------------------------------------------------------------
|   tnblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package tnblog.core.persist.dao;

import tnblog.core.persist.entity.ConfigPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author langhsu
 *
 */
public interface ConfigDao extends JpaRepository<ConfigPO, Long>, JpaSpecificationExecutor<ConfigPO> {
	ConfigPO findByKey(String key);
}
