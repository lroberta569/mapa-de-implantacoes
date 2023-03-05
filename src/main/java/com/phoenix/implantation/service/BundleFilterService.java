package com.phoenix.implantation.service;

import com.phoenix.implantation.dto.BundleDto;
import com.phoenix.implantation.dto.FilterBundleDto;
import com.phoenix.implantation.dto.ProjectDto;
import com.phoenix.implantation.model.bundle.Bundle;
import com.phoenix.implantation.model.bundle.StatusBundle;
import com.phoenix.implantation.model.project.Project;
import com.phoenix.implantation.repository.BundleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Service relacionado ao filtro
 */
@Service
public class BundleFilterService {

    @Autowired
    private BundleRepository bundleRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModelMapper modelMapper;

    public List<BundleDto> findByFilter(FilterBundleDto filterBundleDto) {
        return bundleRepository.findAll(findByFilterSpecification(filterBundleDto)).stream().map(this::bundleToDto).toList();
    }

    private Specification<Bundle> findByFilterSpecification(FilterBundleDto filterBundleDto) {
        return (root, query, criteriaBuilder) -> {
            var predicates = findByFilterCriteria(filterBundleDto, root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private List<Predicate> findByFilterCriteria(FilterBundleDto filterBundleDto, Root<Bundle> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        var project = projectService.findById(filterBundleDto.getProject().getId());
        predicates.add(findByFilterProject(dtoToProject(project), criteriaBuilder, root));

        if (filterBundleDto.getDateDeliveryPreview() != null)
            predicates.add(findByFilterDateDeliveryPreview(filterBundleDto.getDateDeliveryPreview(), criteriaBuilder, root));

        if (filterBundleDto.getDateDelivery() != null)
            predicates.add(findByFilterDateDelivery(filterBundleDto.getDateDelivery(), criteriaBuilder, root));

        if (filterBundleDto.getStatus().getId() != null)
            predicates.add(findByFilterStatusBundle(filterBundleDto.getStatus(), criteriaBuilder, root));

        return predicates;
    }

    private Predicate findByFilterStatusBundle(StatusBundle status, CriteriaBuilder criteriaBuilder, Root<Bundle> root) {
        return criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), status));
    }

    private Predicate findByFilterDateDeliveryPreview(LocalDate dateDeliveryPreview, CriteriaBuilder criteriaBuilder, Root<Bundle> root) {
        return criteriaBuilder.and(criteriaBuilder.equal(root.get("dateDeliveryPreview"), dateDeliveryPreview));
    }

    private Predicate findByFilterDateDelivery(LocalDate dateDelivery, CriteriaBuilder criteriaBuilder, Root<Bundle> root) {
        return criteriaBuilder.and(criteriaBuilder.equal(root.get("dateDelivery"), dateDelivery));
    }

    private Predicate findByFilterProject(Project project, CriteriaBuilder criteriaBuilder, Root<Bundle> root) {
        return criteriaBuilder.and(criteriaBuilder.equal(root.get("project"), project));
    }

    private BundleDto bundleToDto(Bundle bundle) {
        return this.modelMapper.map(bundle, BundleDto.class);
    }

    private Project dtoToProject(ProjectDto projectDto) {
        return modelMapper.map(projectDto, Project.class);
    }
}
