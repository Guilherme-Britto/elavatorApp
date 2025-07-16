package br.com.guilherme.elevator.dto;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ElavetorOutputDTO {

    private List<Integer> leastUsedFloors;
    private List<Character> mostUsedElevators;
    private List<Character> leastUsedElevators;
    private List<Character> peakPeriodMostUsedElevator;
    private List<Character> offPeakPeriodLeastUsedElevator;
    private List<Character> peakPeriodAllElevators;
    private Map<Character, Double> usagePercentagePerElevator;

    public ElavetorOutputDTO() {}
    // Getters e setters

    public List<Integer> getLeastUsedFloors() {
        return leastUsedFloors;
    }

    public void setLeastUsedFloors(List<Integer> leastUsedFloors) {
        this.leastUsedFloors = leastUsedFloors;
    }

    public List<Character> getMostUsedElevators() {
        return mostUsedElevators;
    }

    public void setMostUsedElevators(List<Character> mostUsedElevators) {
        this.mostUsedElevators = mostUsedElevators;
    }

    public List<Character> getLeastUsedElevators() {
        return leastUsedElevators;
    }

    public void setLeastUsedElevators(List<Character> leastUsedElevators) {
        this.leastUsedElevators = leastUsedElevators;
    }

    public List<Character> getPeakPeriodMostUsedElevator() {
        return peakPeriodMostUsedElevator;
    }

    public void setPeakPeriodMostUsedElevator(List<Character> peakPeriodMostUsedElevator) {
        this.peakPeriodMostUsedElevator = peakPeriodMostUsedElevator;
    }

    public List<Character> getOffPeakPeriodLeastUsedElevator() {
        return offPeakPeriodLeastUsedElevator;
    }

    public void setOffPeakPeriodLeastUsedElevator(List<Character> offPeakPeriodLeastUsedElevator) {
        this.offPeakPeriodLeastUsedElevator = offPeakPeriodLeastUsedElevator;
    }

    public List<Character> getPeakPeriodAllElevators() {
        return peakPeriodAllElevators;
    }

    public void setPeakPeriodAllElevators(List<Character> peakPeriodAllElevators) {
        this.peakPeriodAllElevators = peakPeriodAllElevators;
    }

    public Map<Character, Double> getUsagePercentagePerElevator() {
        return usagePercentagePerElevator;
    }

    public void setUsagePercentagePerElevator(Map<Character, Double> usagePercentagePerElevator) {
        this.usagePercentagePerElevator = usagePercentagePerElevator;
    }

    private String formatUsagePercentage() {
        if (usagePercentagePerElevator == null || usagePercentagePerElevator.isEmpty()) {
            return "   (Nenhum dado disponível)";
        }

        StringBuilder sb = new StringBuilder();
        usagePercentagePerElevator.forEach((elevator, percentage) -> {
            sb.append("   - Elevador ").append(elevator).append(": ")
                    .append(String.format(Locale.US, "%.2f", percentage)).append("%\n");
        });
        return sb.toString();
    }

    @Override
    public String toString() {
        return "\nConsiderando que este possa evoluir para um sistema dinâmico, extraímos as seguintes informações estatísticas:\n" +
                "\na. O(s) andar(es) menos utilizado(s) pelos usuários: " + leastUsedFloors +
                "\nb. O(s) elevador(es) mais frequentado(s): " + mostUsedElevators +
                "\n   Período(s) de maior fluxo desse(s) elevador(es): " + peakPeriodMostUsedElevator +
                "\nc. O(s) elevador(es) menos frequentado(s): " + leastUsedElevators +
                "\n   Período(s) de menor fluxo desse(s) elevador(es): " + offPeakPeriodLeastUsedElevator +
                "\nd. O(s) período(s) de maior utilização do conjunto de elevadores: " + peakPeriodAllElevators +
                "\ne. Percentual de uso de cada elevador em relação a todos os serviços prestados:\n" + formatUsagePercentage();
    }
}
