typedef enum {
  MPI_INT,
  MPI_FLOAT,
  MPI_DOUBLE,
  MPI_CHAR
} MPI_Datatype;

typedef struct {
  int MPI_SOURCE;
  int MPI_TAG;
  int MPI_ERROR;
  int size;
} MPI_Status;
#ifdef __CIVLC__
#else
#include<civlc.h>
#endif

#define MPI_STATUS_IGNORE NULL

#define MPI_STATUSES_IGNORE NULL

$comm MPI_COMM_WORLD;// = $comm_create($here, gcomm, __rank);

int sizeofDatatype(MPI_Datatype datatype) {
  switch (datatype) {
  case MPI_INT:
    return sizeof(int);
  case MPI_FLOAT:
    return sizeof(float);
  case MPI_DOUBLE:
    return sizeof(double);
  case MPI_CHAR:
    return sizeof(char);
  default:
    $assert(0, "Unreachable");
  }
}

int MPI_Init() {
  return 0;
}

int MPI_Finalize() {
  return 0;
}


int MPI_Comm_size($comm comm, int *size) {
  *size = $comm_size(comm);
  return 0;
}

int MPI_Comm_rank($comm comm, int *rank) {
  *rank = $comm_place(comm);
  return 0;
}

int MPI_Send(void *buf, int count, MPI_Datatype datatype, int dest,
	     int tag, $comm comm) {
  if (dest >= 0) {
    int size = count*sizeofDatatype(datatype);
    int place = $comm_place(comm);
    $message out = $message_pack(place, dest, tag, buf, size);

    $comm_enqueue(comm, out);
  }
  return 0;
}

int MPI_Recv(void *buf, int count, MPI_Datatype datatype, int source,
	     int tag, $comm comm, MPI_Status *status) {
  if (source >= 0) {
    $message in = $comm_dequeue(comm, source, tag);
    int size = count*sizeofDatatype(datatype);

    $message_unpack(in, buf, size);
    if (status != MPI_STATUS_IGNORE) {
      status->size = $message_size(in);
      status->MPI_SOURCE = $message_source(in);
      status->MPI_TAG = $message_tag(in);
      status->MPI_ERROR = 0;
    }
  }
  return 0;
}
