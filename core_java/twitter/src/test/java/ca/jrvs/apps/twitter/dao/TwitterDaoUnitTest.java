package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.Util.TweetUtil;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void create() {
    //test failed request
    String hashTag = "#abc";
    String text = "@Test this is a test for twitter api " + hashTag + " " + System.currentTimeMillis();
    double lat = 1d;
    double lon = -1d;

    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));

    try{
      dao.create(TweetUtil.buildTweet(text,lon,lat));
      fail();
    }catch (RuntimeException e){
      assertTrue(true);
    }
  }

  @Test
  public void findById() {
  }

  @Test
  public void deleteById() {
  }
}