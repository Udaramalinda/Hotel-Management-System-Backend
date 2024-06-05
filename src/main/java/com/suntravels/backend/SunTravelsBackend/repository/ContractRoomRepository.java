package com.suntravels.backend.SunTravelsBackend.repository;

import com.suntravels.backend.SunTravelsBackend.model.Contract;
import com.suntravels.backend.SunTravelsBackend.model.ContractRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRoomRepository extends JpaRepository<ContractRoom, Integer>
{
    List<ContractRoom> getContractRoomByContract( Contract contract );

    List<ContractRoom> findByContractOrderByMaxAdultsDesc( Contract contract );

    List<ContractRoom> findByContractOrderByMaxAdults( Contract contract );
}
