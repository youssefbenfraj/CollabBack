package tn.esprit.espritcollabbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.espritcollabbackend.entities.Modules;
import tn.esprit.espritcollabbackend.entities.Niveau;
import tn.esprit.espritcollabbackend.repositories.ModulesRepository;

import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ModulesServiceIMP implements IModules{
    private ModulesRepository modulesRepository;


    @Override
    public Modules addModules(Modules M) {
        return modulesRepository.save(M);
    }

    @Override
    public Modules retrievebyId(Long idM) {
        return modulesRepository.findById(idM).orElse(null);
    }

    @Override
    public List<Modules> retrieveAll() {
        return modulesRepository.findAll();
    }

    @Override
    public void deleteById(Long idM) {
        modulesRepository.deleteById(idM);
    }

    @Override
    public Modules updateModules(Modules MD, long id) {
        Modules m =modulesRepository.findById(id).get();
        if (MD.getNomModule()!=null){
            m.setNomModule(MD.getNomModule());
        }
        if (MD.getNiveau()!=null){
            m.setNiveau(MD.getNiveau());
        }



        return modulesRepository.save(m);

    }
    public Map<String, Long> countModulesByNiveau() {
        List<Object[]> results = modulesRepository.countModulesByNiveau();
        Map<String, Long> moduleStatisticsByNiveau = new HashMap<>();

        for (Object[] result : results) {
            Niveau niveau = (Niveau) result[0]; // Cast to Niveau enum
            Long count = (Long) result[1];
            moduleStatisticsByNiveau.put(niveau.toString(), count); // Use enum's string representation
        }

        return moduleStatisticsByNiveau;
    }
}
