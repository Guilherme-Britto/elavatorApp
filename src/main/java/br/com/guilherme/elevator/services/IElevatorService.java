package br.com.guilherme.elevator.services;

import br.com.guilherme.elevator.dto.ElavetorOutputDTO;
import br.com.guilherme.elevator.dto.ElevatorUsageDTO;
import java.util.List;
import java.util.Map;

public interface IElevatorService {

    List<Integer> getLeastUsedFloors(List<ElevatorUsageDTO> usageList);

    List<Character> getMostUsedElevators(List<ElevatorUsageDTO> usageList);

    List<Character> getLeastUsedElevators(List<ElevatorUsageDTO> usageList);

    List<Character> getPeakPeriods(List<ElevatorUsageDTO> usageList, List<Character> filteredElevators);

    Map<Character, Double> getElevatorUsagePercentage(List<ElevatorUsageDTO> usageList);

    ElavetorOutputDTO analyze(List<ElevatorUsageDTO> usageList);
}