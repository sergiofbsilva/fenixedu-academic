/*
 * Created on 12/Aug/2004
 *  
 */
package DataBeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Dominio.IGroupProperties;
import Dominio.IGroupPropertiesExecutionCourse;

/**
 * @author joaosa & rmalo 12/Aug/2004
 */
public class InfoGroupPropertiesWithInfoGroupPropertiesExecutionCourseWaiting extends
        InfoGroupProperties {

    public void copyFromDomain(IGroupProperties groupProperties) {
        super.copyFromDomain(groupProperties);
        
        if (groupProperties != null) {
        	List infoGroupPropertiesExecutionCourse = new ArrayList();
            Iterator iterGroupPropertiesExecutionCourse = groupProperties.getGroupPropertiesExecutionCourse().iterator();
            
            while(iterGroupPropertiesExecutionCourse.hasNext()){
            	IGroupPropertiesExecutionCourse groupPropertiesExecutionCourse =  
            		(IGroupPropertiesExecutionCourse) iterGroupPropertiesExecutionCourse.next();
            	if(groupPropertiesExecutionCourse.getProposalState().getState().intValue()==3){
            	infoGroupPropertiesExecutionCourse.add(InfoGroupPropertiesExecutionCourse
            			.newInfoFromDomain(groupPropertiesExecutionCourse));
            	}
            }
        	
            setInfoGroupPropertiesExecutionCourse(infoGroupPropertiesExecutionCourse);
        }
    }

    public static InfoGroupProperties newInfoFromDomain(
            IGroupProperties groupProperties) {
        InfoGroupPropertiesWithInfoGroupPropertiesExecutionCourseWaiting infoGroupProperties = null;
        if (groupProperties != null) {
            infoGroupProperties = new InfoGroupPropertiesWithInfoGroupPropertiesExecutionCourseWaiting();
            infoGroupProperties.copyFromDomain(groupProperties);
        }

        return infoGroupProperties;
    }
}