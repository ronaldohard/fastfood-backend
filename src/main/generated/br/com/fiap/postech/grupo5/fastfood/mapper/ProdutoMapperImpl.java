package br.com.fiap.postech.grupo5.fastfood.mapper;

import br.com.fiap.postech.grupo5.fastfood.dto.ProdutoDTO;
import br.com.fiap.postech.grupo5.fastfood.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-18T18:47:05-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Microsoft)"
)
@Component
public class ProdutoMapperImpl implements ProdutoMapper {

    @Override
    public Produto toEntity(ProdutoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Produto produto = new Produto();

        return produto;
    }

    @Override
    public ProdutoDTO toDto(Produto produto) {
        if ( produto == null ) {
            return null;
        }

        ProdutoDTO produtoDTO = new ProdutoDTO();

        return produtoDTO;
    }

    @Override
    public List<ProdutoDTO> toDtoList(List<Produto> produtos) {
        if ( produtos == null ) {
            return null;
        }

        List<ProdutoDTO> list = new ArrayList<ProdutoDTO>( produtos.size() );
        for ( Produto produto : produtos ) {
            list.add( toDto( produto ) );
        }

        return list;
    }
}
