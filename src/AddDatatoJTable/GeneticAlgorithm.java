package AddDatatoJTable;

import java.util.stream.IntStream;

public class GeneticAlgorithm {



    //declaring variables
    private int populationSize;
    private double rateOfMutation;
    private double rateOfCrossover;
    private int count;
    protected int tournamentSize;



   //initializing variables
    public GeneticAlgorithm(int populationSize, double rateOfMutation, double rateOfCrossover, int count, int tournamentSize) {
        this.populationSize = populationSize;
        this.rateOfMutation = rateOfMutation;
        this.rateOfCrossover = rateOfCrossover;
        this.count = count;
        this.tournamentSize = tournamentSize;
    }

   // Tries to come up with a fitness above one to insure that there are zero clashes
    public double calcFitness(Individual individual, Schedule schedule) {

        Schedule threadSchedule = new Schedule(schedule);
        threadSchedule.createClasses(individual);


        int clashes = threadSchedule.calcClashes(populationSize);
        double fitness = (double) (clashes)/100;
        individual.setFitness(fitness);
        return fitness;

    }
    // different terminating conditions
    public boolean isTerminating(int generationsCount, int maxGenerations) {
        return (generationsCount > maxGenerations);
    }
    // different terminating conditions
    public boolean isTerminating(Population population) {
        return population.getFittest(0).getFitness() >= 100.0;
    }
    // Chooses the most fit individuals to continue in selection.
    public Individual selectionFunction(Population population) {

        Population tournament = new Population(this.tournamentSize);
        population.shuffle();
        for (int i = 0; i < this.tournamentSize; i++) {
            Individual tournamentIndividual = population.getIndividual(i);
            tournament.setIndividual(i, tournamentIndividual);
        }
        return tournament.getFittest(0);
    }

    public Population crossoverPopulation(Population population) {

        Population newPopulation = new Population(population.size());

        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual parent1 = population.getFittest(populationIndex);
            if (this.rateOfCrossover > Math.random() && populationIndex > this.count) {
                Individual offspring = new Individual(parent1.getChromosomeLength());
                Individual parent2 = selectionFunction(population);
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    //using half parents 1 genes and the other half of parent 2
                    if (0.50 > Math.random()) {
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    } else {
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }

                newPopulation.setIndividual(populationIndex, offspring);
            } else {

                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        return newPopulation;
    }

    // creates an entity to have information copied to and be used for further best fit
    public Population mutatingPopulation(Population population, Schedule schedule) {

        Population newPopulation = new Population(this.populationSize);

        double bestFitness = population.getFittest(0).getFitness();
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual individual = population.getFittest(populationIndex);
            Individual randomIndividual = new Individual(schedule);
            double adaptiveMutationRate = this.rateOfMutation;
            if (individual.getFitness() > population.getAvgFitness()) {
                double fitnessDelta1 = bestFitness - individual.
                        getFitness();
                double fitnessDelta2 = bestFitness - population.
                        getAvgFitness();
                adaptiveMutationRate = (fitnessDelta1 / fitnessDelta2) *
                        this.rateOfMutation;
            }
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {

                if (populationIndex > this.count) {
                  //does this gene need mutation or not
                    if (this.rateOfMutation > Math.random()) {
                        individual.setGene(geneIndex,randomIndividual.getGene(geneIndex));
                    }
                }
            }
            newPopulation.setIndividual(populationIndex, individual);
        }

        return newPopulation;
    }

    public Population initializingPopulation(Schedule schedule) {

        Population population = new Population(this.populationSize, schedule);
        return population;
    }


    public void calcPopulation(Population population, Schedule schedule) {
        IntStream.range(0, population.size()).parallel()
                .forEach(i -> this.calcFitness(population.getIndividual(i),
                        schedule));

        double populationFitness = 0;
        for (Individual individual : population.getIndividuals()) {
            populationFitness += this.calcFitness(individual, schedule);
        }
        population.setPopulationFitness(populationFitness);
    }
}
