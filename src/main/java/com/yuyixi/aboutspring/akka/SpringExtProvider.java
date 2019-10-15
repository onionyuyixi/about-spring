package com.yuyixi.aboutspring.akka;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;

/**
 * @author yangcong
 * @date 2019/10/15 14:39
 */

public class SpringExtProvider extends AbstractExtensionId<SpringExt> {
    private static SpringExtProvider provider = new SpringExtProvider();

    public static SpringExtProvider getInstance() {
        return provider;
    }

    @Override
    public SpringExt createExtension(ExtendedActorSystem extendedActorSystem) {
        return new SpringExt();
    }

}
