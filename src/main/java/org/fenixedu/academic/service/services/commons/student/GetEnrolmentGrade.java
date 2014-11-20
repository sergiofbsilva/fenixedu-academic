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
package org.fenixedu.academic.service.services.commons.student;

import org.fenixedu.academic.domain.Enrolment;
import org.fenixedu.academic.domain.EnrolmentEvaluation;
import org.fenixedu.academic.dto.InfoEnrolmentEvaluation;
import org.fenixedu.academic.dto.InfoPerson;

/**
 * @author Nuno Nunes (nmsn@rnl.ist.utl.pt)
 * @author Joana Mota (jccm@rnl.ist.utl.pt)
 */
public class GetEnrolmentGrade {

    final public InfoEnrolmentEvaluation run(final Enrolment enrolment) {
        if (enrolment == null) {
            return null;
        }

        return getInfoLatestEvaluation(enrolment.getLatestEnrolmentEvaluation());
    }

    final private InfoEnrolmentEvaluation getInfoLatestEvaluation(final EnrolmentEvaluation latestEvaluation) {

        final InfoEnrolmentEvaluation infolatestEvaluation = InfoEnrolmentEvaluation.newInfoFromDomain(latestEvaluation);
        if (latestEvaluation.getPerson() != null) {
            infolatestEvaluation.setInfoPerson(InfoPerson.newInfoFromDomain(latestEvaluation.getPerson()));
            if (latestEvaluation.getPersonResponsibleForGrade() != null) {
                infolatestEvaluation.setInfoPersonResponsibleForGrade(InfoPerson.newInfoFromDomain(latestEvaluation
                        .getPersonResponsibleForGrade()));
            }
        }

        return infolatestEvaluation;
    }

}