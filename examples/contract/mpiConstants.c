
/*@
  @ requires x == \mpi_comm_rank;
  @ ensures  (\mpi_empty_in(0)) && (\mpi_empty_out(0));
  @*/
int foo(int x) {
  return x;
}

int main() {

  return 0;
}
