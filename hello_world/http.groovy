def connection = new URL("http://www.baidu.com").openConnection()
connection.setRequestMethod('GET')
connection.doOutput = true


def writer = new OutputStreamWriter(connection.outputStream)
writer.flush()
writer.close()
connection.connect()


def respText = connection.content.text