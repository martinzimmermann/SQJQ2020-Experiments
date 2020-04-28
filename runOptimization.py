import random
import array
import numpy
import os
import re
import csv

from deap import algorithms
from deap import base
from deap import creator
from deap import tools

#SCENARIO = "Weather Scenario"
SCENARIO = "Temporary Fault Scenario"

creator.create("FitnessMax", base.Fitness, weights=(1.0,))
creator.create("Individual", list, fitness=creator.FitnessMax)

toolbox = base.Toolbox()
# Attribute generator
toolbox.register("attr_bool", random.random)
# Structure initializers
toolbox.register("individual", tools.initRepeat, creator.Individual, toolbox.attr_bool, 9)
toolbox.register("population", tools.initRepeat, list, toolbox.individual)

def evalOneMax(individual):
    f = open("programs\\temp.rule", "w")
    f.write(f'lidar_ok.\nradar_ok.\ncamera_ok.\n' +
    f'camera_ok -> +objectDetected actions.detectObjectWithCamera [{individual[0]},{individual[1]},{individual[2]}].\n' + 
    f'lidar_ok -> +objectDetected actions.detectObjectWithLiDAR [{individual[3]},{individual[4]},{individual[5]}].\n' +
    f'radar_ok -> +objectDetected actions.detectObjectWithRADAR [{individual[6]},{individual[7]},{individual[8]}].\n' +
    f'objectDetected -> #objectProcessed -objectDetected actions.processObject.')
    f.close()

    os.chdir(SCENARIO)

    ruleFile = "programs/temp.rule"
    os.system("gradle -PruleFile=\"../" + ruleFile + "\" fatJar --quiet")

    myCmd = os.popen('java -jar .\\build\\libs\\simulation-all-1.0-SNAPSHOT.jar').read()
    result = float(re.compile(r'Successes %: ([0-9]+\.[0-9]*)').search(myCmd).group(1))

    os.chdir("..")
    return (result,)

def checkBounds(min, max):
    def decorator(func):
        def wrapper(*args, **kargs):
            offspring = func(*args, **kargs)
            for child in offspring:
                for i in range(len(child)):
                    if child[i] > max:
                        child[i] = max
                    elif child[i] < min:
                        child[i] = min
            return offspring
        return wrapper
    return decorator

toolbox.register("evaluate", evalOneMax)
toolbox.register("mate", tools.cxTwoPoint)
toolbox.register("mutate", tools.mutGaussian, mu=0.2, sigma=0.5, indpb=0.2)
toolbox.register("select", tools.selTournament, tournsize=3)

toolbox.decorate("mutate", checkBounds(0, 1))

def main():
    pop = toolbox.population(n=100)
    hof = tools.HallOfFame(5)
    stats = tools.Statistics(lambda ind: ind.fitness.values)
    stats.register("avg", numpy.mean)
    stats.register("std", numpy.std)
    stats.register("min", numpy.min)
    stats.register("max", numpy.max)
    
    pop, log = algorithms.eaSimple(pop, toolbox, cxpb=0.5, mutpb=0.2, ngen=60,
                                stats=stats, halloffame=hof, verbose=True)
    print(hof)
    with open(f'hof {SCENARIO}.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        for individual in hof:
            writer.writerow(individual)

main()