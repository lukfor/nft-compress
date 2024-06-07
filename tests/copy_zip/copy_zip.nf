process COPY_ZIP {

  input:
    path zip

  output:
     path "output.zip", emit: zip


  """
	cp ${zip} output.zip
  """

}
