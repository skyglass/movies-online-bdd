import searchService from "../searchService";
import moxios from 'moxios';

describe('searchService', () => {
   beforeEach(() => {
      moxios.install();
   });

   afterEach(() => {
      moxios.uninstall();
   });
   const query = 'Bobby';

   describe('when request is unsuccessful', () => {
      it('returns an empty array', async () => {
         moxios.stubRequest('/api/search?term=Bobby', {
            status: 500
         });

         expect(await searchService(query)).toEqual([]);
      });
   });

   describe('when request responds with no results', () => {
      it('returns empty results', async () => {
         moxios.stubRequest('/api/search?term=Bobby', {
            status: 200,
            response: []
         });

         expect(await searchService(query)).toEqual([]);
      });
   });

   describe('when request responds with results', () => {
      it('returns empty results', async () => {
         const data = [{
            albumTitle: 'Subway entrance thing',
            imageUrl: 'http://example.com/image.png',
            artists: [{name: 'Bobby Joel'}]
         }];

         moxios.stubRequest('/api/search?term=Bobby', {
            status: 200,
            response: data
         });

         expect(await searchService(query)).toEqual(data);
      });
   });
});