package rj.cefet.sacapi.servico;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Setor;
import rj.cefet.sacapi.repositorio.RepositorioDeSetor;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoDeSetor {
    private RepositorioDeSetor repositorioDeSetor;

    public ServicoDeSetor(RepositorioDeSetor repositorioDeSetor) {
        this.repositorioDeSetor = repositorioDeSetor;
    }

    public Page<Setor> findAllSetores(Pageable pageable){
        return repositorioDeSetor.findAll(pageable);
    }

    public Setor salvar(Setor setor){
        return repositorioDeSetor.save(setor);
    }

    public Setor findById(String idSetor){
        Optional<Setor> setorOptional = repositorioDeSetor.findById(idSetor);
        if (setorOptional.isEmpty()) throw new EntityNotFoundException("Setor de id '%s' não encontrado.".formatted(idSetor));
        return setorOptional.get();
    }
}
