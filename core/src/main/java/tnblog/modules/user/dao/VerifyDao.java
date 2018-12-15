/*
+--------------------------------------------------------------------------
|   tnblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package tnblog.modules.user.dao;

import tnblog.modules.user.entity.Verify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author langhsu on 2015/8/14.
 */
public interface VerifyDao extends JpaRepository<Verify, Long>, JpaSpecificationExecutor<Verify> {
    Verify findByUserIdAndType(long userId, int type);
    Verify findByUserId(long userId);
}
