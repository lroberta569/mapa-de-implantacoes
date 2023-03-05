package com.phoenix.implantation.service;

import com.phoenix.implantation.dto.ItemDto;
import com.phoenix.implantation.exception.InternalServerErrorException;
import com.phoenix.implantation.exception.NotFoundException;
import com.phoenix.implantation.model.item.Item;
import com.phoenix.implantation.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juan Campos do Nascimento
 * @email juan.nascimento@arphoenix.com.br
 * @description Repositorio relacionado ao item;
 */

@Log4j2
@Service
@RequiredArgsConstructor

public class ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;


    /**
     * Método para buscar todos os itens com paginação
     *
     * @param pageable
     * @return Page<ItemDto>
     */
    public Page<ItemDto> findAll(Pageable pageable) throws InternalServerErrorException {
        try {
            Page<ItemDto> listItem = itemRepository.findAll(pageable).map(this::itemToDto);
            return listItem;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum item encontrado");
        }
    }

    /**
     * Método para buscar um item de um pacote
     *
     * @param id
     * @param pageable
     * @return Page<ItemDto>
     */
    public Page<ItemDto> findByBundleId(Long id, Pageable pageable) throws InternalServerErrorException {
        try {
            Page<ItemDto> listItem = itemRepository.findByBundleId(id, pageable).map(this::itemToDto);
            return listItem;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum item encontrado");
        }
    }

    /**
     * Método para buscar todos os itens obsoletos por listagem
     *
     * @param id
     * @return list<ItemDto>
     */
    public List<ItemDto> findByObsolete(Long id) throws InternalServerErrorException {
        try {
            List<ItemDto> listItem = itemRepository.findByBundleIdAndObsolete(id, false).stream().map(this::itemToDto).toList();
            return listItem;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum item encontrado");
        }
    }

    /**
     * Método para buscar todos os iten obsoletos por paginação
     *
     * @param id
     * @param pageable
     * @return Page<ItemDto>
     */
    public Page<ItemDto> findByObsolete(Long id, Pageable pageable) throws InternalServerErrorException {
        try {
            Page<ItemDto> listItem = itemRepository.findByBundleIdAndObsolete(id, false, pageable).map(this::itemToDto);
            return listItem;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum item encontrado");
        }
    }

    /**
     * Método para buscar todos os itens por pacote
     *
     * @param id
     * @return List<ItemDto>
     */
    public List<ItemDto> findByBundleId(Long id) throws InternalServerErrorException {
        try {
            List<ItemDto> listItem = itemRepository.findByBundleId(id).stream().map(this::itemToDto).toList();
            return listItem;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum item encontrado");
        }


    }

    /**
     * Método para buscar um item
     *
     * @param id
     * @return ItemDto
     */
    public ItemDto findById(Long id) {
        return itemToDto(itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Item não encotrado")));
    }

    /**
     * Método para cadastar um item
     *
     * @param itemDto
     * @return ItemDto
     */
    public ItemDto create(ItemDto itemDto) {

        try {
            var item = dtoToItem(itemDto);
            item.setObsolete(false);
            return itemToDto(itemRepository.save(item));
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao cadastrar o item");
        }
    }

    /**
     * Método para atualizar um item
     *
     * @param id
     * @param itemDto
     * @return ItemDto
     */
    public ItemDto update(Long id, ItemDto itemDto) {
        try {
            var itemDb = dtoToItem(findById(id));
            if (itemDto.getIncrementVersion()) {
                return incrementVersion(itemDto, itemDb);
            } else {
                itemDto.setId(itemDb.getId());
                itemDto.setVersion(itemDb.getVersion());
                itemDto.setBundle(itemDb.getBundle());
                var item = dtoToItem(itemDto);
                itemRepository.save(item);
                return itemDto;
            }
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new InternalServerErrorException("Ocorreu um erro ao atualizar o pacote");
        }
    }


    /**
     * Método para incrementar versão no item e setar a versão antiga como obsolete=true
     *
     * @param itemDto
     * @param itemDb
     * @return ItemDto
     */
    public ItemDto incrementVersion(ItemDto itemDto, Item itemDb) throws InternalServerErrorException {
        try {
            itemDb.setObsolete(true);
            itemRepository.save(itemDb);
            itemDto.setVersion(itemDb.getVersion() + 1L);
            itemDto.setBundle(itemDb.getBundle());
            var item = dtoToItem(itemDto);
            itemRepository.save(item);
            return itemDto;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao incrementar a versão do item");
        }
    }

    /**
     * Método para conveter dto em item
     *
     * @param itemDto
     * @return ItemDto
     */
    private Item dtoToItem(ItemDto itemDto) {
        return modelMapper.map(itemDto, Item.class);
    }

    /**
     * Método para converte item em ItemDto
     *
     * @param item
     * @return ItemDto
     */
    private ItemDto itemToDto(Item item) {
        return modelMapper.map(item, ItemDto.class);
    }
}
