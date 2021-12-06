package AddDatatoJTable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.*;


public class Population {

    private Individual population[];
    private double populationFitness = -1;

    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    public Population(int populationSize, int Length) {
        this.population = new Individual[populationSize];

        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            Individual individual = new Individual(Length);
            this.population[individualCount] = individual;
        }
    }

    public Individual[] getIndividuals() {
        return this.population;
    }

    public Individual getFittest(int offset) {
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual number1, Individual number2) {
                if (number1.getFitness() > number2.getFitness()) {
                    return -1;
                } else if (number1.getFitness() < number2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });
        return this.population[offset];
    }

    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }

    public int size() {
        return this.population.length;
    }

    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }

    public Individual getIndividual(int offset) {
        return population[offset];
    }

    public void shufflePopulation() {
        Random rnd = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Individual atindex = population[index];
            population[index] = population[i];
            population[i] = atindex;
        }
    }

    public Population(int populationSize, Schedule schedule) {
        // Initial population
        this.population = new Individual[populationSize];
        // Loop over population size
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            // Create individual
            Individual individual = new Individual(schedule);
            // Add individual to population
            this.population[individualCount] = individual;
        }
    }

    public double getAvgFitness(){
        // function to get the average fitness
        if (this.populationFitness == -1) {
            double totalFitness = 0;
            for (Individual individual : population) {
                totalFitness += individual.getFitness();
            }
            this.populationFitness = totalFitness;
        }
        return populationFitness / this.size();
    }


}
