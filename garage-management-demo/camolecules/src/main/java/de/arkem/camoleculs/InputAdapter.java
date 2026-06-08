package de.arkem.camoleculs;

import org.jmolecules.stereotype.Stereotype;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PACKAGE, ElementType.TYPE })
@Documented
@Stereotype
public @interface InputAdapter {
}
