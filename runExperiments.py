import os, shutil, re, csv, datetime

programs = [
    {"name": "Unmodified"},
    {"name": "Aging"},
    {"name": "Generated Temp"},
    {"name": "Generated Weather"},
    ]

examples = [
    {"name": 'Temporary Fault Scenario', "program" : "simulation-all-1.0-SNAPSHOT.jar", "match" : r'Successes %: ([0-9]+\.[0-9]*)'},
    {"name": 'Weather Scenario', "program" : "simulation-all-1.0-SNAPSHOT.jar", "match" : r'Successes %: ([0-9]+\.[0-9]*)'},
    ]

numberOfExecutions = 3000

print("{}: Start".format(datetime.datetime.now()))
with open('experiments_results.csv', 'w', newline='') as csvfile:
    csvwriter = csv.writer(csvfile, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)

    myCmd = None
    for example in examples:
        for program in programs:
            results = [example["name"] + "|" + program["name"]]
            os.chdir(example["name"])
            ruleFile = "programs/" + program["name"] +".rule"
            os.system("gradle -PruleFile=\"../" + ruleFile + "\" fatJar --quiet")
            
            outputs = []
            for i in range(numberOfExecutions):
                myCmd = os.popen('java -jar .\\build\\libs\\' + example["program"]).read()
                result = float(re.compile(example["match"]).search(myCmd).group(1))
                outputs.append(result)
            
            print("{}: {} | {}: {}".format(datetime.datetime.now(), program["name"], example["name"], "done"))
            results.extend(outputs)
            
            if os.path.isdir("build"):
                shutil.rmtree("build")
            os.chdir("..")
        
            csvwriter.writerow(results)
            csvfile.flush()