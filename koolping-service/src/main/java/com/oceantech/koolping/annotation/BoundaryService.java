package com.oceantech.koolping.annotation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * This annotation indicates the annotated class is a transactional boundary.
 * <p/>
 * It serves as a meta annotation of {@link org.springframework.stereotype.Service @Service}, allowing
 * for implementation classes to be auto-detected through classpath scanning.
 * <p/>
 * This also marks the annotated class as {@link org.springframework.transaction.annotation.Transactional @Transactional}
 * with propagation level {@link org.springframework.transaction.annotation.Propagation#REQUIRES_NEW REQUIRES_NEW} to
 * indicate that a new transaction is started before method entry.
 *
 * @author Sanjoy Roy
 */

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
public @interface BoundaryService {
}
