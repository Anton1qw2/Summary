#include <iostream>
#include <fstream>
#include <jpeglib.h>
#include <stdlib.h>
unsigned char *raw_image = NULL;
// Параметры изображения по умолчанию
// Будут измененны при прочтении изображения в методе read_jpeg_file
int width = 300;
int height = 300;
int bytes_per_pixel = 3;
J_COLOR_SPACE color_space = JCS_RGB;

//Данный метод производит декомпрессию изображения
//На вход путь к изображению. Выход 1 успех, -1 ошибка
int read_jpeg_file( char *filename )
{
    struct jpeg_decompress_struct cinfo;
    struct jpeg_error_mgr jerr;

    JSAMPROW row_pointer[1];

    std::FILE *infile = fopen( filename, "rb" );
    unsigned long location = 0;
    int i = 0;

    if ( !infile )
    {
        printf("Error opening jpeg file %s\n!", filename );
        return -1;
    }

    cinfo.err = jpeg_std_error( &jerr );

    jpeg_create_decompress( &cinfo );

    jpeg_stdio_src( &cinfo, infile );

    jpeg_read_header( &cinfo, TRUE );

    //Заносим параметры читаемого изображения в глобальные переменные
    jpeg_start_decompress( &cinfo );
    width = cinfo.output_width;
    height = cinfo.output_height;
    color_space = cinfo.out_color_space;
    bytes_per_pixel = cinfo.output_components;
    raw_image = (unsigned char*)malloc( cinfo.output_width*cinfo.output_height*cinfo.num_components );
    //Выделение памяти для декомпрессии изображения
    row_pointer[0] = new unsigned char [cinfo.output_width*cinfo.num_components];
    //Посмтрочно заносим изображение в память
    while( cinfo.output_scanline < cinfo.image_height )
    {
        jpeg_read_scanlines( &cinfo, row_pointer, 1 );
        for( i=0; i<cinfo.image_width*cinfo.num_components;i++)
            raw_image[location++] = row_pointer[0][i];
    }
    //Очистка ресурсов
    jpeg_finish_decompress( &cinfo );
    jpeg_destroy_decompress( &cinfo );
    delete[] row_pointer[0];
    fclose( infile );

    return 1;
}

// Метод записи изображения из области памяти в файл в формате JPEG
// Вход, путь куда требуется сохранить выходное изображение. ./res/output.jpg
// Выход 1 успех. -1 ошибка выполнения
int write_jpeg_file( char *filename, int quality )
{
    struct jpeg_compress_struct cinfo;
    struct jpeg_error_mgr jerr;

    JSAMPROW row_pointer[1];
    FILE *outfile = fopen( filename, "wb" );

    if ( !outfile )
    {
        printf("Error opening output jpeg file %s\n!", filename );
        return -1;
    }
    cinfo.err = jpeg_std_error( &jerr );
    jpeg_create_compress(&cinfo);
    jpeg_stdio_dest(&cinfo, outfile);


    cinfo.image_width = width;
    cinfo.image_height = height;
    cinfo.input_components = bytes_per_pixel;
    cinfo.in_color_space = color_space;
    jpeg_set_defaults( &cinfo );

    jpeg_set_quality(&cinfo, quality, TRUE);

    jpeg_start_compress( &cinfo, TRUE );
    //Построчно записываем в файл
    while( cinfo.next_scanline < cinfo.image_height )
    {
        row_pointer[0] = &raw_image[ cinfo.next_scanline * cinfo.image_width *  cinfo.input_components];
        jpeg_write_scanlines( &cinfo, row_pointer, 1 );
    }
    //очистка ресурсов
    jpeg_finish_compress( &cinfo );
    jpeg_destroy_compress( &cinfo );
    fclose( outfile );

    return 1;
}


// Запуск с параметрами, в начале путь к загружаемому изображению например .res/input.jpg затем путь к выходному изображению ./res/output.jpg + параметр сжатия

// /home/anton/MediaSDK/res/input.jpg  /home/anton/MediaSDK/res/output.jpg 80


//При успешном завершении в консоли появится надпись Successfully! (код выхода 0), при возникновении ошибок код выхода -1.

int main( int argc, char  *args[])
{
  int quality = 80;
  char *infilename = "/home/anton/MediaSDK/res/input.jpg", *outfilename = "/home/anton/MediaSDK/res/output1.jpg";
  if (!(argc ==4)){
      std::cout << "Invalid parameters"<< std::endl;
      return -1;
  }
      quality = atoi (args[3]);
      infilename = args[1];
      outfilename = args[2];

    if( read_jpeg_file( infilename ) > 0 )
    {
      if( write_jpeg_file( outfilename , quality) < 0 ) return -1;
    } else {
    return -1;
  }
  std::cout << "Successfully!"<< std::endl;
  return 0;
}
