export interface DTOResultWrapper<T> {
  code: number;
	message: string;
	info: T;
}
