import threading
import copy
class Pop:
    """
    size = 0
    global main
    global birds
    global obstacles
    global windObj
    global bird
    """
    
    def __init__(self, size, bird, main):
        self.size = size
        self.string = "bird"
        self.main = main
        self.bird = bird
        self.birds = [copy.deepcopy(bird) for i in range(self.size)]


    def run(self, interval):
        self.birds = [copy.deepcopy(self.bird) for i in range(self.size)]
        num = interval
        #print("hey", num)
        for i in range(len(self.birds)):
            #global vars()[string+str(i)]
            vars()[self.string+str(i)] = threading.Thread(target = self.main, args=(self.birds[i], copy.deepcopy(num),))
            #print(str(vars()[self.string+str(i)]))
            (vars()[self.string+str(i)]).start()
            #(vars()[self.string+str(i)]).join()
            num+=1
        checking = threading.Thread(target = self.check)
        checking.start()

    def check(self):
        while not len(self.birds)==0:
            #print(bird0.isAlive())
            for i, bird in enumerate(self.birds):
                if bird.isDead():
                    try:
                        del self.birds[i]
                    except:
                        pass
                    #print(len(self.birds))
        #self.run()
        #print("done")
