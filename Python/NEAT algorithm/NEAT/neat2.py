import population as popu
import Genes as genes
import numpy as np
from numpy.random import choice
import copy

class NEAT:
    def __init__(self):
        self.fitness = []
        self.gen = 1
        self.size = 1
        
    
    def createPopulation(self, size, bird, main, numInputs, numOutputs):
        self.organisms = genes.Genes(numInputs, numOutputs, size)
        self.organisms.newGenomes()
        self.pop = popu.Pop(size, bird, main)
        self.fitness = [1]*size
        #self.fitness = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        self.size = size
        

    def runPopulation(self):
        self.pop.run()
        
    def getPopulation(self):
        return self.pop.birds
    
    def setFitness(self, num, fitness):
        self.fitness[num] = fitness

    def getBestFitness(self):
        return max(self.fitness)

    def decide(self, ii, num):
        creature = self.organisms.organs[num]
        result = self.organisms.eval(ii, creature)
        return result
        

    def reproduce(self):
        self.fitness = self.organisms.speciate(self.organisms.organs,
                                                self.fitness,
                                                self.gen)
        #print(self.organisms.species)
        #print(self.fitness)

        species = copy.deepcopy(self.organisms.species)
        species.reverse()
        organisms = copy.deepcopy(self.organisms.organs)
        organisms.reverse()
        fitnesses = copy.deepcopy(self.fitness)
        fitnesses.reverse()

        for i in range(len(self.organisms.representatives)):
            specnum = i+1
            if specnum in species:
                num = round((species.count(specnum))/4)
                g = [i for i, e in enumerate(copy.deepcopy(species)) if e == specnum]
                for j in range(num):
                    del species[g[num]]
                    del organisms[g[num]]
                    del fitnesses[g[num]]

        self.organisms.organs.clear()
        self.organisms.species.clear()
        self.fitness.clear()
        organisms.reverse()
        species.reverse()
        fitnesses.reverse()
        self.organisms.organs = copy.deepcopy(organisms)
        self.organisms.species = copy.deepcopy(species)
        self.fitness = copy.deepcopy(fitnesses)
                    
                
                
                
                       
        for i in range(len(self.organisms.organs)):
            self.fitness[i] = self.organisms.shared_fitness(self.organisms.organs[i],
                                                            self.fitness[i],
                                                            self.organisms.organs,
                                                            self.organisms.species)
        #print(self.fitness)

            


        s = sum(self.fitness)
        #print(self.fitness)
        #print(s)
        #print(self.organisms.organs)
        gs = copy.deepcopy(self.organisms.organs)
        cgs = []
        for i in range(self.size):
            intercross = np.random.uniform(0, 100)
            index1 = 0
            index2 = 0
            if (intercross <= 10):
                draw = choice(a = np.arange(len(self.organisms.organs)), replace=True,
                          size = 2,
                  p=[self.fitness[i]/s for i in range(len(self.fitness))])
                
                index1 = gs[draw.tolist()[0]]
                index2 = gs[draw.tolist()[1]]
            else:
                draw = choice(a = np.arange(len(self.organisms.organs)), size = 1, p = [self.fitness[i]/s for i in range(len(self.fitness))])
                index1 = gs[copy.deepcopy(draw.tolist()[0])]
                rindex1 = copy.deepcopy(draw.tolist()[0])
                #print("rindex1 ", rindex1)
                if (self.organisms.species.count(self.organisms.species[(rindex1)]) == 1):
                    index2 = copy.deepcopy(index1)
                    rindex2 = copy.deepcopy(rindex1)
                else:   
                    while True:
                        draw = choice(a = np.arange(len(self.organisms.organs)), size = 1, p = [self.fitness[i]/s for i in range(len(self.fitness))])
                        rindex2 = copy.deepcopy(draw.tolist()[0])
                            
                        #print("rindex2 ", rindex2)
                        if (bool(self.organisms.species[(rindex1)] == self.organisms.species[rindex2])) or (bool(not(rindex2 == rindex1))):
                            break
                    index2 = gs[copy.deepcopy(draw.tolist()[0])]
            #self.organisms.organs.index(index1)

            c = self.organisms.crossover(index1, self.fitness[self.organisms.organs.index(index1)],
                                     index2, self.fitness[self.organisms.organs.index(index2)])
            cgs.append(copy.deepcopy(c))
        self.organisms.organs.clear()
        self.organisms.organs = copy.deepcopy(cgs)
        #print(self.organisms.organs, len(self.organisms.organs))
        del cgs[:]
        self.fitness.clear()
        self.fitness = [1]*self.size
        self.gen+=1

        

        #print(self.organisms.organs)
        #print(self.organisms.species)
        #print(self.organisms.representatives)
        #print()
        
        self.runPopulation()
