package com.iemr.ecd.utils.redis;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RedisStorage.class, LettuceConnectionFactory.class})
@ExtendWith(SpringExtension.class)
class RedisStorageDiffblueTest {
  @Autowired
  private RedisStorage redisStorage;
  /**
   * Method under test: {@link RedisStorage#getSessionObject(String)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetSessionObject() throws Exception {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Failed to create Spring context.
    //   Attempt to initialize test context failed with
    //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@7c875200 testClass = com.iemr.ecd.utils.redis.DiffblueFakeClass1191, locations = [], classes = [com.iemr.ecd.utils.redis.RedisStorage, org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@5b1bb066, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@28dd2525, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@3317b80c], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
    //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
    //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
    //       at java.base/java.util.Optional.map(Optional.java:260)
    //   See https://diff.blue/R026 to resolve this issue.

    // Arrange and Act
    redisStorage.getSessionObject("Key");
  }
}
