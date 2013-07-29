package com.oceantech.koolping.infrastructure.persistence;

import com.oceantech.koolping.domain.model.SocialUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Set;

/**
 * Data access methods used in implementing Spring Social's ConnectionRepository
 * and UsersConnectionRepository interfaces.
 * <p/>
 * One important note is that in Spring Social the code and documentation use
 * the term "userId" to mean a unique identifier of local accounts. It does not
 * have to be a numeric unique id, such as a database primary key value. For
 * integration with Spring Security the "userId" should be whatever identifier
 * your users log in with (e.g. a username, an email address, etc.). In this
 * application it is the local account's username.
 * <p/>
 * Also important to note is that Spring Social technically allows for
 * many-to-many relationships between local users and accounts from a
 * social network (e.g. Twitter). For example, you can associate one Twitter
 * account with several local accounts, and associate one local account with
 * several Twitter accounts. This is reflected in the methods of this interface.
 * <p/>
 * This allows for maximum flexibility for applications using Spring Social,
 * however most often apps will probably only allow a one-to-one relationship
 * (e.g. one Twitter account connects with only one local account and vice versa).
 * In this demo application users are not allowed to associate one social account
 * with multiple local accounts. They will get an error if they attempt to do so.
 * Similarly attempting to conenct two social accounts from the same provider
 * with one local account will result in an error.
 * <p/>
 * There are some common arguments to these methods:
 * <p/>
 * userId - unique id of a local account (for this app, it's the username)
 * providerId - id of a social provider (e.g. "twitter", "facebook", etc.)
 * providerUserId - id of an account on a social provider's network (e.g. a Twitter handle, Facebook user id, etc.)
 */
public interface SocialUserRepository extends CrudRepository<SocialUser, Long> {

    //  void save(SocialUser socialUser);
    List<SocialUser> findByUserId(String userId);

    @Query("SELECT u FROM SocialUser u WHERE u.userId=?1 and u.providerId=?2")
    List<SocialUser> findByUserIdAndProviderId(String userId, String providerId);

    @Query("SELECT u FROM SocialUser u WHERE u.userId= ?1 and u.providerUserId in (?2)")
    List<SocialUser> findByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserId);

    @Query("SELECT u FROM SocialUser u WHERE u.userId= ?1 and u.providerId=?2 and u.providerUserId=?3")
    SocialUser get(String userId, String providerId, String providerUserId);

    @Query("SELECT u FROM SocialUser u WHERE u.userId=?1 and u.providerId=?2")
    List<SocialUser> findPrimaryByUserIdAndProviderId(String userId, String providerId);

    @Query("SELECT max(u.rank) FROM SocialUser u WHERE u.userId=?1 and u.providerId=?2")
    Integer selectMaxRankByUserIdAndProviderId(String userId, String providerId);

    @Query("SELECT u.userId FROM SocialUser u WHERE u.providerId= ?1 and u.providerUserId= ?2")
    List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId);

    @Query("SELECT u.userId FROM SocialUser u WHERE u.providerId=?1 and u.providerUserId in (?2)")
    List<String> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserId);

    // void delete(SocialUser socialUser);
}
