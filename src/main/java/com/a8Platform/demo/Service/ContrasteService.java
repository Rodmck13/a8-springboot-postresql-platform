package com.a8Platform.demo.Service;

import com.a8Platform.demo.Entities.Contraste;
import com.a8Platform.demo.Repository.ContrasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class ContrasteService {

  @Autowired ContrasteRepository contrasteRepository;

  public void save(float contrasteNuevo) {
    Calendar date = Calendar.getInstance();
    try {
      Contraste contraste = new Contraste();
      contraste.setCreated_at(date);
      contraste.setMililitrosRestantes(contrasteNuevo);
      contrasteRepository.save(contraste);
    } catch (Exception e) {

    }
  }

  public boolean restarContraste(float contrasteARestar) {
    try {
      float contrasteActual = getLast();
      if (contrasteActual - contrasteARestar > 0) {
        save(contrasteActual - contrasteARestar);
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  public boolean recargarContraste(float contrasteASumar) {
    try {
      float contrasteActual = getLast();
      save(contrasteActual + contrasteASumar);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean modificarContraste(float contraste) {
    try {
      save(contraste);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public float getLast() {
    float contrasteResult = 0;
    Optional<Contraste> contraste = contrasteRepository.findTopByOrderByIdDesc();
    if (contraste.isPresent()) {
      contrasteResult = contraste.get().getMililitrosRestantes();
      return contrasteResult;
    } else {
      return contrasteResult;
    }
  }
}
