package br.com.guilherme.elevator.services;

import br.com.guilherme.elevator.dto.ElevatorUsageDTO;
import br.com.guilherme.elevator.dto.ElavetorOutputDTO;

import java.util.*;
import java.util.stream.Collectors;

public class ElevatorService implements IElevatorService {

    @Override
    public List<Integer> getLeastUsedFloors(List<ElevatorUsageDTO> usageList) {
        Map<Integer, Long> floorCount = usageList.stream()
                .collect(Collectors.groupingBy(ElevatorUsageDTO::getAndar, Collectors.counting()));

        long min = Collections.min(floorCount.values());

        return floorCount.entrySet().stream()
                .filter(e -> e.getValue() == min)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> getMostUsedElevators(List<ElevatorUsageDTO> usageList) {
        Map<Character, Long> elevatorCount = getElevatorCounts(usageList);
        long max = Collections.max(elevatorCount.values());

        return elevatorCount.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> getLeastUsedElevators(List<ElevatorUsageDTO> usageList) {
        Map<Character, Long> elevatorCount = getElevatorCounts(usageList);
        long min = Collections.min(elevatorCount.values());

        return elevatorCount.entrySet().stream()
                .filter(e -> e.getValue() == min)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Map<Character, Long> getElevatorCounts(List<ElevatorUsageDTO> usageList) {
        return usageList.stream()
                .collect(Collectors.groupingBy(ElevatorUsageDTO::getElevador, Collectors.counting()));
    }

    @Override
    public List<Character> getPeakPeriods(List<ElevatorUsageDTO> usageList, List<Character> filterElevators) {
        Map<Character, Long> periodCount;

        if (filterElevators == null) {
            periodCount = usageList.stream()
                    .collect(Collectors.groupingBy(ElevatorUsageDTO::getTurno, Collectors.counting()));
        } else {
            periodCount = usageList.stream()
                    .filter(e -> filterElevators.contains(e.getElevador()))
                    .collect(Collectors.groupingBy(ElevatorUsageDTO::getTurno, Collectors.counting()));
        }

        long max = Collections.max(periodCount.values());

        return periodCount.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Character, Double> getElevatorUsagePercentage(List<ElevatorUsageDTO> usageList) {
        Map<Character, Long> count = getElevatorCounts(usageList);
        long total = usageList.size();

        return count.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> (e.getValue() * 100.0) / total
                ));
    }

    @Override
    public ElavetorOutputDTO analyze(List<ElevatorUsageDTO> usageList) {
        ElavetorOutputDTO output = new ElavetorOutputDTO();

        List<Character> mostUsed = getMostUsedElevators(usageList);
        List<Character> leastUsed = getLeastUsedElevators(usageList);

        output.setLeastUsedFloors(getLeastUsedFloors(usageList));
        output.setMostUsedElevators(mostUsed);
        output.setLeastUsedElevators(leastUsed);
        output.setPeakPeriodMostUsedElevator(getPeakPeriods(usageList, mostUsed));
        output.setOffPeakPeriodLeastUsedElevator(getPeakPeriods(usageList, leastUsed));
        output.setPeakPeriodAllElevators(getPeakPeriods(usageList, null));
        output.setUsagePercentagePerElevator(getElevatorUsagePercentage(usageList));

        return output;
    }
}
