package com.ecommerce.admin.settings.state;

import com.ecommerce.common.entity.Country;
import com.ecommerce.common.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StateRestController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping("/states/list_by_country/{id}")
    public List<StateDTO> listByCountry(@PathVariable("id") Integer countryId){

        List<State> listStates = stateRepository.findByCountryOrderByNameAsc(new Country((countryId)));
        List<StateDTO> result = new ArrayList<>();


        for (State state: listStates){
            System.out.println(state.getName());
            result.add(new StateDTO(state.getId(), state.getName()));
        }

        return result;
    }

    @PostMapping("/states/save")
    public String save(@RequestBody State state){
        State savedState = stateRepository.save(state);
        return String.valueOf(savedState.getId());
    }

    @RequestMapping(value = "/states/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id){

        stateRepository.deleteById(id);
    }
}
