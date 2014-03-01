package com.linkedin.uif.scheduler.local;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.linkedin.uif.scheduler.JobLock;

/**
 * Unit test for {@link LocalJobLock}.
 */
@Test(groups = {"com.linkedin.uif.scheduler"})
public class LocalJobLockTest {

    public void testLocalJobLock() throws Exception {
        final JobLock lock = new LocalJobLock();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Assert.assertTrue(lock.tryLock());
                    Thread.sleep(2000);
                    lock.unlock();
                } catch (Exception e) {
                    // Ignored
                }
            }
        });
        thread1.start();

        Thread.sleep(1000);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Assert.assertFalse(lock.tryLock());
                    Thread.sleep(2000);
                    Assert.assertTrue(lock.tryLock());
                    Thread.sleep(1000);
                    lock.unlock();
                } catch (Exception e) {
                    // Ignored
                }
            }
        });
        thread2.start();
    }
}
