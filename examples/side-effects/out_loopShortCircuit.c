int main()
{
  int i = 0;
  int j = 1;
  while(1)
  {
    int $sef$0;
    if(!(i > (-1)))
      $sef$0 = $false;
    else
    {
      int $sef$3 = i < 10;
      i = i + 1;
      $sef$0 = $sef$3;
    }
    if(!$sef$0)
      break;
    j = i;
  }
  for(; 1; j = j + 1)
  {
    int $sef$1;
    if(!(i > (-1)))
      $sef$1 = $false;
    else
    {
      int $sef$4 = i < 10;
      i = i + 1;
      $sef$1 = $sef$4;
    }
    if(!$sef$1)
      break;
    j = i + j;
  }
  {
    int $sef$2;
    do
    {
      j = i;
      if(!(i > (-1)))
        $sef$2 = $false;
      else
      {
        int $sef$5 = i < 10;
        i = i + 1;
        $sef$2 = $sef$5;
      }
    }while($sef$2);
  }
}

