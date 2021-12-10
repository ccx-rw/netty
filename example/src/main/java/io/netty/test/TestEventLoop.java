package io.netty.test;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author rw
 * @date 2021/12/10
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        //获取事件循环组
        EventLoopGroup group = new NioEventLoopGroup(2);
//        System.out.println(NettyRuntime.availableProcessors());
//        //获取下一个事件循环对象-生成两个后是轮询的，策略是什么样的呢？
//        System.out.println(group.next());
//        System.out.println(group.next());
//        System.out.println(group.next());
//        System.out.println(group.next());

        //执行普通任务
        group.next().execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("ok");
        });
        group.next().scheduleAtFixedRate(()->{
            log.debug("schedule run");
        },0,1, TimeUnit.SECONDS);
        log.debug("main");
    }
}
