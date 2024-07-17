//package ucad.sn.master2.service.Impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ucad.sn.master2.service.*;
//
//@Service
//public class StatisticServiceImpl implements StatisticService {
//
//    @Autowired
//    private EtudiantService etudiantService;
//
//    @Autowired
//    private EnseignantService enseignantService;
//
//    @Autowired
//    private EnseignantResponsableService responsableClasseService;
//
//    @Autowired
//    private ClasseService classeService;
//
//    @Override
//    public long countStudents() {
//        return etudiantService.countStudents();
//    }
//
//    @Override
//    public long countTeachers() {
//        return enseignantService.countTeachers();
//    }
//
//    @Override
//    public long countResponsibleTeachers() {
//        return responsableClasseService.countResponsibleTeachers();
//    }
//
//    @Override
//    public long countClasses() {
//        return classeService.countClasses();
//    }
//}
