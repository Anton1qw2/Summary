import cv2
import numpy as np
import os


#указания пути для сети
net = cv2.dnn.readNetFromTensorflow(os.path.join('models','gan','generator.pb'),
                                    os.path.join('models','gan','generator.pbtxt'))

netd = cv2.dnn.readNetFromTensorflow(os.path.join('models','gan','discriminator.pb'),
                                    os.path.join('models','gan','discriminator.pbtxt'))

#Найстройка параметров видео
fourcc = cv2.VideoWriter_fourcc(*'XVID')
videoout = cv2.VideoWriter('output.avi',fourcc, 20.0, (480,480))
#кол во итераций для перехода от одного изображения к другмоу
iteration = 100
#Найстройка входных значений
inp = np.random.uniform(low =-1.0, high = 1.0, size = [1, 64]).astype(np.float32)
inpout = np.random.uniform(low =-1.0, high = 1.0, size = [1, 64]).astype(np.float32)
tmp = inpout - inp
tmp = tmp/iteration

#Настройка сети
net.setInput(inp)
out = net.forward()
net.setInput(inpout)
out2 = net.forward()
netd.setInput(out)
outd = netd.forward()
netd.setInput(out2)
outd2 = netd.forward()
out = np.reshape(out, [3, 64, 64])
out = np.transpose(out, [1, 2, 0])
out2 = np.reshape(out2, [3, 64, 64])
out2 = np.transpose(out2, [1, 2, 0])
outd = np.reshape(outd, [3, 64, 64])
outd = np.transpose(outd, [1, 2, 0])
outd2 = np.reshape(outd2, [3, 64, 64])
outd2 = np.transpose(outd2, [1, 2, 0])

#Нормализация выхода
out = out + 1
out = out / 2
np.clip(out, 0, 1)
out = cv2.resize(out, (480, 480))
out = cv2.cvtColor(out, cv2.COLOR_RGB2BGR)

outd = outd + 1
outd = outd / 2
np.clip(outd, 0, 1)
outd = cv2.resize(outd, (480, 480))
outd = cv2.cvtColor(outd, cv2.COLOR_RGB2BGR)

out2 = out2 + 1
out2 = out2 / 2
np.clip(out2, 0, 1)
out2 = cv2.resize(out2, (480, 480))
out2= cv2.cvtColor(out2, cv2.COLOR_RGB2BGR)

outd2 = outd2 + 1
outd2 = outd2 / 2
np.clip(outd2, 0, 1)
outd2 = cv2.resize(outd2, (480, 480))
outd2 = cv2.cvtColor(outd2, cv2.COLOR_RGB2BGR)
#Вывод "крайних" лиц
cv2.imshow("from", outd)
cv2.imshow("to", outd2)
count = 0
while cv2.waitKey(1) < 0:
    if count>=iteration:
        videoout.release()
        cv2.destroyAllWindows()
        cv2.waitKey()
        break
    print ("Frame: ",count)
    inp = inp + tmp
    net.setInput(inp)
    out = net.forward()
    netd.setInput(out)
    outd = netd.forward()

    out = np.reshape(out, [3,64,64])
    out = np.transpose(out, [1,2,0])

    outd = np.reshape(outd, [3,64,64])
    outd = np.transpose(outd, [1,2,0])

    count += 1

    out = out+1
    out = out/2
    np.clip(out, 0, 1)
    out = cv2.resize(out, (480, 480))
    out = cv2.cvtColor(out , cv2.COLOR_RGB2BGR)

    outd = outd+1
    outd = outd/2
    np.clip(outd, 0, 1)
    outd = cv2.resize(outd, (480, 480))
    outd = cv2.cvtColor(outd , cv2.COLOR_RGB2BGR)
#для просмотра промежуточного результата раскоментить:
#    cv2.imshow('interval', outd)
#    cv2.imshow('Generator', out)
    outd = outd *250
    videoout.write(np.uint8(outd))
#    cv2.imwrite(str(count) + '.jpeg', outd)
