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
package org.fenixedu.academic.ui.renderers.providers;

import java.util.List;

import org.fenixedu.academic.domain.Degree;
import org.fenixedu.academic.domain.ExecutionCourse;
import org.fenixedu.academic.ui.struts.action.academicAdministration.executionCourseManagement.ExecutionCourseBean;
import org.fenixedu.academic.ui.struts.action.academicAdministration.executionCourseManagement.MergeExecutionCourseDA.DegreesMergeBean;

import pt.ist.fenixWebFramework.rendererExtensions.converters.DomainObjectKeyConverter;
import pt.ist.fenixWebFramework.renderers.DataProvider;
import pt.ist.fenixWebFramework.renderers.components.converters.BiDirectionalConverter;
import pt.ist.fenixWebFramework.renderers.components.converters.Converter;

import com.google.common.collect.Lists;

public class DestinationExecutionCoursesProvider implements DataProvider {

    @Override
    @SuppressWarnings("serial")
    public Converter getConverter() {

        return new BiDirectionalConverter() {

            @Override
            @SuppressWarnings("rawtypes")
            public Object convert(final Class type, final Object value) {
                final ExecutionCourseBean result = new ExecutionCourseBean();
                result.setDestinationExecutionCourse((ExecutionCourse) new DomainObjectKeyConverter().convert(type, value));
                return result;
            }

            @Override
            public String deserialize(final Object object) {
                String result = "";

                if (object != null && object instanceof ExecutionCourseBean) {
                    final ExecutionCourseBean bean = (ExecutionCourseBean) object;

                    if (bean.getDestinationExecutionCourse() != null) {
                        result = bean.getDestinationExecutionCourse().toString();
                    }
                }

                return result;
            }
        };
    }

    @Override
    public Object provide(final Object source, final Object currentValue) {
        final List<ExecutionCourseBean> result = Lists.newLinkedList();

        final DegreesMergeBean bean = (DegreesMergeBean) source;
        final Degree degree = bean.getDestinationDegree();

        for (final ExecutionCourse iter : SourceExecutionCoursesProvider.getExecutionCourses(degree, bean.getAcademicInterval())) {

            final ExecutionCourseBean resultBean = new ExecutionCourseBean();
            resultBean.setDegree(degree);
            resultBean.setDestinationExecutionCourse(iter);

            result.add(resultBean);
        }

        return result;
    }

}
