package com.oceantech.koolping.service;

import com.oceantech.koolping.infrastructure.persistence.SocialUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
 @Service
public class SocialUserServiceImpl implements SocialUserService {

    private final static Log logger = LogFactory.getLog(SocialUserServiceImpl.class);
    @Autowired
    private SocialUserRepository socialUserDAO;


    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
    private TextEncryptor textEncryptor;
    @Value("${social.crypto.password}")
    private String encryptionPassword;
    @Value("${social.crypto.enabled:false}")
    private boolean encryptCredentials;

    @PostConstruct
    public void initializeTextEncryptor() {
        textEncryptor = Encryptors.text(encryptionPassword, KeyGenerators.string().generateKey());
    }

    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        logger.info("Method call findUserIdsWithConnection Get ProviderId " +key.getProviderId()+ " Get ProviderUserId "+key.getProviderUserId());
          List <String> userIds =socialUserDAO.findUserIdsByProviderIdAndProviderUserId(key.getProviderId(), key.getProviderUserId());
        if (userIds==null|| userIds.size()==0){
            logger.info("Method call findUserIdsWithConnection Get ProviderId " +key.getProviderId()+ " Get ProviderUserId "+key.getProviderUserId()+" has failed");
        }

        return userIds;

    }

    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        logger.info("Method call findUserIdsConnectedTo ");
        return new HashSet<String>(socialUserDAO.findUserIdsByProviderIdAndProviderUserIds(providerId, providerUserIds));
    }

    public ConnectionRepository createConnectionRepository(String userId) {
        logger.info("Method call createConnectionRepository ");
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        return new SocialUserConnectionRepositoryImpl(
                userId,
                socialUserDAO,
                connectionFactoryLocator,
                (encryptCredentials ? textEncryptor : null)
        );
    }
}
