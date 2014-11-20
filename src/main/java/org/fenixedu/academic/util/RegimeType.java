/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Academic.
 *
 * FenixEdu Academic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Academic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Academic.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on Dec 5, 2005
 *	by mrsp
 */
package org.fenixedu.academic.util;

import java.util.Locale;

import org.fenixedu.bennu.core.i18n.BundleUtil;
import org.fenixedu.commons.i18n.I18N;

public enum RegimeType {

    INTEGRAL_TIME, PARTIAL_TIME, EXCLUSIVENESS;

    public String getName() {
        return name();
    }

    public String localizedName(Locale locale) {
        return BundleUtil.getString(Bundle.ENUMERATION, locale, qualifiedName());
    }

    protected String qualifiedName() {
        return this.getClass().getSimpleName() + "." + name();
    }

    protected String localizedName() {
        return localizedName(I18N.getLocale());
    }

    public String getLocalizedName() {
        return localizedName();
    }

}