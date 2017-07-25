package com.yaoobs.anotherplay.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yaoobs on 2017/7/25.
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {

}
