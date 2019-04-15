import threading
import copy
import gc
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


    def run(self):
        gc.collect()
        self.birds = [copy.deepcopy(self.bird) for i in range(self.size)]
        for i in range(len(self.birds)):
            num = i
            #global vars()[string+str(i)]
            vars()[self.string+str(i)] = threading.Thread(target = self.main, args=(self.birds[i], num,))
            #print(str(vars()[self.string+str(i)]))
            (vars()[self.string+str(i)]).start()
            #(vars()[self.string+str(i)]).join()
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
        gc.collect()
        #self.run()
        #print("done")
