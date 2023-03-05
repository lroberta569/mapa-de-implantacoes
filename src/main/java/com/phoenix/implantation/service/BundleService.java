package com.phoenix.implantation.service;

import com.phoenix.implantation.dto.BundleDto;
import com.phoenix.implantation.dto.FilterBundleDto;
import com.phoenix.implantation.exception.InternalServerErrorException;
import com.phoenix.implantation.exception.NotFoundException;
import com.phoenix.implantation.model.bundle.Bundle;
import com.phoenix.implantation.repository.BundleRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Larissa Silva
 * @email larissa.silva@arphoenix.com.br
 * @description Serviços relacionados ao pacote
 */
@Log4j2
@Service
public class BundleService {

    @Autowired
    private BundleRepository bundleRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BundleFilterService bundleFilterService;

    /**
     * Método para buscar o pacote por id
     *
     * @param id
     * @return BundleDto
     */
    public BundleDto findById(Long id) {
        return bundleToDto(bundleRepository.findById(id).orElseThrow(() -> new NotFoundException("Pacote não encontrado")));
    }

    /**
     * Método para listar todos os pacotes com paginação pelo id do projeto
     *
     * @param id
     * @param pageable
     * @return Page<BundleDto>
     */
    public Page<BundleDto> findByProjectId(Long id, Pageable pageable) throws InternalServerErrorException {
        try {
            Page<BundleDto> listBundles = bundleRepository.findByProjectId(
                    projectService.findById(id).getId(), pageable).map(this::bundleToDto);
            return listBundles;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum pacote encontrado");
        }
    }

    /**
     * Método para listar todos os pacotes com paginação pelo id do projeto
     *
     * @param id
     * @return List<BundleDto>
     */
    public List<BundleDto> findBundleByProjectId(Long id) throws InternalServerErrorException {
        try {
            List<BundleDto> listBundles = bundleRepository.findByProjectId(id).stream().map(this::bundleToDto).toList();
            return listBundles;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum pacote encontrado");
        }
    }

    /**
     * Método para listar todas as ultimas versões dos pacotes com paginação pelo id do projeto
     *
     * @param id
     * @param pageable
     * @return Page<BundleDto>
     */
    public Page<BundleDto> findByObsolete(Long id, Pageable pageable) throws InternalServerErrorException {
        try {
            Page<BundleDto> listBundles = bundleRepository.findByProjectIdAndObsolete(id, false, pageable).map(this::bundleToDto);
            return listBundles;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum pacote encontrado");
        }
    }

    /**
     * Método para listar todas as ultimas versões dos pacotes com paginação pelo id do projeto
     *
     * @param id
     * @return List<BundleDto>
     */
    public List<BundleDto> findByObsolete(Long id) throws InternalServerErrorException {
        try {
            List<BundleDto> listBundles = bundleRepository.findByProjectIdAndObsolete(id, false).stream().map(this::bundleToDto).toList();
            return listBundles;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum pacote encontrado");
        }
    }

    /**
     * Método para criar um pacote
     *
     * @param bundleDto
     * @return BundleDto
     */
    public BundleDto create(BundleDto bundleDto) throws InternalServerErrorException {
        try {
            var bundle = dtoToBundle(bundleDto);
            bundle.setObsolete(false);
            return bundleToDto(bundleRepository.save(bundle));
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao cadastrar o pacote");
        }
    }

    /**
     * Método para atualizar um pacote
     *
     * @param bundleDto
     * @return BundleDto
     */
    public BundleDto update(Long id, BundleDto bundleDto) throws InternalServerErrorException {
        try {
            var bundleDb = dtoToBundle(findById(id));
            if (bundleDto.getIncrementVersion()) {
                return incrementVersion(bundleDto, bundleDb);
            } else {
                bundleDto.setId(bundleDb.getId());
                bundleDto.setVersion(bundleDb.getVersion());
                bundleDto.setProject(bundleDb.getProject());
                var bundle = dtoToBundle(bundleDto);
                bundleRepository.save(bundle);
                return bundleDto;
            }
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new InternalServerErrorException("Ocorreu um erro ao atualizar o pacote");
        }
    }

    /**
     * Método para incrementar versão no pacote e setar a versão antiga como obsolete=true
     *
     * @param bundleDto
     * @param bundleDb
     * @return BundleDto
     */
    public BundleDto incrementVersion(BundleDto bundleDto, Bundle bundleDb) throws InternalServerErrorException {
        try {
            bundleDb.setObsolete(true);
            bundleRepository.save(bundleDb);
            bundleDto.setVersion(bundleDb.getVersion() + 1L);
            bundleDto.setProject(bundleDb.getProject());
            var bundle = dtoToBundle(bundleDto);
            bundleRepository.save(bundle);
            return bundleDto;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao incrementar a versão do pacote");
        }
    }

    /**
     * Método para filtrar pacotes
     *
     * @param filterBundleDto
     * @return List<BundleDto>
     */
    public List<BundleDto> findBundleByFilter(FilterBundleDto filterBundleDto) throws InternalServerErrorException {
        try {
            List<BundleDto> filteredBundles = bundleFilterService.findByFilter(filterBundleDto);
            return filteredBundles;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum pacote encontrado");
        }
    }

    /**
     * Método para converter um BandleDto para Bunlde
     *
     * @param bundleDto
     * @return Bundle
     */
    private Bundle dtoToBundle(BundleDto bundleDto) {
        return this.modelMapper.map(bundleDto, Bundle.class);
    }

    /**
     * Método para converter um Bundle para BundleDto
     *
     * @param bundle
     * @return BundleDto
     */
    private BundleDto bundleToDto(Bundle bundle) {
        return modelMapper.map(bundle, BundleDto.class);
    }
}
