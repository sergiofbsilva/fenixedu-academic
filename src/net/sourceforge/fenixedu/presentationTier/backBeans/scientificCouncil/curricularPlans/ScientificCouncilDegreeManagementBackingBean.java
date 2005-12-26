package net.sourceforge.fenixedu.presentationTier.backBeans.scientificCouncil.curricularPlans;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.model.SelectItem;

import net.sourceforge.fenixedu.applicationTier.Filtro.exception.FenixFilterException;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.Degree;
import net.sourceforge.fenixedu.domain.GradeScale;
import net.sourceforge.fenixedu.domain.IDegree;
import net.sourceforge.fenixedu.domain.degree.BolonhaDegreeType;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.presentationTier.Action.sop.utils.ServiceUtils;
import net.sourceforge.fenixedu.presentationTier.backBeans.base.FenixBackingBean;

public class ScientificCouncilDegreeManagementBackingBean extends FenixBackingBean {
    private final ResourceBundle scouncilBundle = getResourceBundle("ServidorApresentacao/ScientificCouncilResources");
    private final ResourceBundle enumerationBundle = getResourceBundle("ServidorApresentacao/EnumerationResources");
    private final ResourceBundle domainExceptionBundle = getResourceBundle("ServidorApresentacao/DomainExceptionResources");
    private final String NO_SELECTION = "noSelection";  
    
    private Integer degreeId;
    private IDegree degree;
    private String name;
    private String nameEn;
    private String acronym;
    private String bolonhaDegreeType;
    private String gradeScale;
    private Double ectsCredits;

    public List<IDegree> getBolonhaDegrees() throws FenixFilterException, FenixServiceException {
        Object[] args = { Degree.class };
        List<IDegree> allDegrees = (List<IDegree>) ServiceUtils.executeService(null, "ReadAllDomainObject", args);
        
        List<IDegree> result = new ArrayList<IDegree>();
        for (IDegree degree : allDegrees) {
            if (degree.getBolonhaDegreeType() != null) {
                result.add(degree);
            }
        }
        
        return result;
    }
    
    public Integer getDegreeId() {
        return (degreeId == null) ? (degreeId = getAndHoldIntegerParameter("degreeId")) : degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }
    
    public IDegree getDegree() throws FenixFilterException, FenixServiceException {
        return (degree == null) ? (degree = (IDegree) readDomainObject(Degree.class, getDegreeId())) : degree;
    }
    
    public String getName() throws FenixFilterException, FenixServiceException {
        return (name == null && getDegree() != null) ? (name = getDegree().getNome()) : name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    public String getNameEn() throws FenixFilterException, FenixServiceException {
        return (nameEn == null && getDegree() != null) ? (nameEn = getDegree().getNameEn()) : nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getAcronym() throws FenixFilterException, FenixServiceException {
        return (acronym == null && getDegree() != null) ? (acronym = getDegree().getAcronym()) : acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getBolonhaDegreeType() throws FenixFilterException, FenixServiceException {
        return (bolonhaDegreeType == null && getDegree() != null) ? (bolonhaDegreeType = getDegree().getBolonhaDegreeType().getName()) : bolonhaDegreeType;
    }

    public void setBolonhaDegreeType(String bolonhaDegreeType) {
        this.bolonhaDegreeType = bolonhaDegreeType;
    }

    public String getGradeScale() throws FenixFilterException, FenixServiceException {
        return (gradeScale == null && getDegree() != null) ? (gradeScale = getDegree().getGradeScale().getName()) : gradeScale;
    }

    public void setGradeScale(String gradeScale) {
        this.gradeScale = gradeScale;
    }

    public Double getEctsCredits() throws FenixFilterException, FenixServiceException {
        return (ectsCredits == null && getDegree() != null) ? (ectsCredits = getDegree().getEctsCredits()) : ectsCredits;
    }

    public void setEctsCredits(Double ectsCredits) {
        this.ectsCredits = ectsCredits;
    }

    public List<SelectItem> getBolonhaDegreeTypes() {
        List<SelectItem> result = new ArrayList<SelectItem>();
        
        result.add(new SelectItem(this.NO_SELECTION, scouncilBundle.getString("choose")));
        result.add(new SelectItem(BolonhaDegreeType.DEGREE.name(), enumerationBundle.getString(BolonhaDegreeType.DEGREE.getName()) + " (3 anos)"));
        result.add(new SelectItem(BolonhaDegreeType.INTEGRATED_MASTER_DEGREE.name(), enumerationBundle.getString(BolonhaDegreeType.INTEGRATED_MASTER_DEGREE.getName())  + " (5 anos)"));
        result.add(new SelectItem(BolonhaDegreeType.MASTER_DEGREE.name(), enumerationBundle.getString(BolonhaDegreeType.MASTER_DEGREE.getName())  + " (2 anos)"));
        
        return result;
    }

    public List<SelectItem> getGradeScales() {
        List<SelectItem> result = new ArrayList<SelectItem>();
        
        result.add(new SelectItem(this.NO_SELECTION, scouncilBundle.getString("choose")));
        result.add(new SelectItem(GradeScale.TYPE20.name(), enumerationBundle.getString(GradeScale.TYPE20.name())));
        result.add(new SelectItem(GradeScale.TYPE5.name(), enumerationBundle.getString(GradeScale.TYPE5.name())));
        
        return result;
    }
    
    public String createDegree() {
        if (this.bolonhaDegreeType.equals(this.NO_SELECTION)) {// || this.gradeScale.equals(this.NO_SELECTION)) {
            this.setErrorMessage(scouncilBundle.getString("choose.request"));
            return "";
        }
        
        Object[] args = { this.name, this.nameEn, this.acronym, BolonhaDegreeType.valueOf(this.bolonhaDegreeType), this.ectsCredits, null }; //GradeScale.valueOf(this.gradeScale) };
        return changeDegree("CreateDegree", args, "degree.created", "error.creatingDegree");
    }
    
    public String editDegree() {
        if (this.bolonhaDegreeType.equals(this.NO_SELECTION)) {// || this.gradeScale.equals(this.NO_SELECTION)) {
            this.setErrorMessage(scouncilBundle.getString("choose.request"));
            return "";
        }

        Object[] args = { this.getDegreeId(), this.name, this.nameEn, this.acronym, BolonhaDegreeType.valueOf(this.bolonhaDegreeType), this.ectsCredits, null }; //GradeScale.valueOf(this.gradeScale) };
        return changeDegree("EditDegree", args, "degree.edited", "error.editingDegree");
    }
    
    private String changeDegree(String serviceName, Object[] args, String successfulMsg, String errorMsg) {
        try {
            ServiceUtils.executeService(this.getUserView(), serviceName, args);
        } catch (FenixFilterException e) {
            this.addErrorMessage(scouncilBundle.getString("error.notAuthorized"));
            return "curricularPlansManagement";
        } catch (FenixServiceException e) {
            this.setErrorMessage(scouncilBundle.getString(e.getMessage()));
            return "";
        } catch (DomainException e) {
            this.setErrorMessage(domainExceptionBundle.getString(e.getMessage()));
            return "";
        } catch (Exception e) {
            this.addErrorMessage(scouncilBundle.getString(errorMsg));
            return "curricularPlansManagement";
        } 
        
        this.addInfoMessage(scouncilBundle.getString(successfulMsg));
        return "curricularPlansManagement";
    }
    
    public String deleteDegree() {
        Object[] args = { this.getDegreeId() };
        return changeDegree("DeleteDegree", args, "degree.deleted", "error.deletingDegree");
    }

}
