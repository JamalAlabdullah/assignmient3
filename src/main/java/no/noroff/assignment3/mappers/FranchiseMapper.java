package no.noroff.assignment3.mappers;


import no.noroff.assignment3.moduls.Franchise;
import no.noroff.assignment3.moduls.dtos.FranchiseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {

    FranchiseDTO franchiseToFranchiseDTO(Franchise franchise);


}
